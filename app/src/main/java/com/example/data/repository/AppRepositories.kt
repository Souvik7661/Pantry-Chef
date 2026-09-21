package com.example.data.repository

import com.example.data.engine.RecipeMatchingEngine
import com.example.data.local.AppDatabase
import com.example.data.local.CookingHistoryEntity
import com.example.data.local.FavoriteEntity
import com.example.data.local.MealPlanEntity
import com.example.data.local.PantryEntity
import com.example.data.local.ShoppingItemEntity
import com.example.data.local.UserPreferencesEntity
import com.example.data.model.MealType
import com.example.data.model.Recipe
import com.example.data.model.RecipeIngredient
import com.example.data.sample.SeedData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class RecipeRepository(
    private val database: AppDatabase
) {
    private val customAiRecipes = mutableListOf<Recipe>()
    private val allRecipes: List<Recipe> = SeedData.recipes
    private var cachedFullList: List<Recipe> = allRecipes
    private val recipeLookupMap = java.util.concurrent.ConcurrentHashMap<String, Recipe>()

    init {
        allRecipes.forEach { recipeLookupMap[it.id] = it }
    }

    fun getAllRecipes(): List<Recipe> = cachedFullList

    fun addCustomAiRecipe(recipe: Recipe) {
        if (customAiRecipes.none { it.id == recipe.id }) {
            customAiRecipes.add(0, recipe)
            recipeLookupMap[recipe.id] = recipe
            cachedFullList = customAiRecipes + allRecipes
        }
    }

    fun getRecipeById(id: String): Recipe? = recipeLookupMap[id]

    fun searchRecipes(
        query: String,
        cuisineFilter: String? = null,
        mealTypeFilter: MealType? = null,
        maxCookingTime: Int? = null,
        dietaryFilters: List<String> = emptyList()
    ): List<Recipe> {
        val q = query.trim().lowercase()
        return allRecipes.filter { recipe ->
            val matchesQuery = q.isEmpty() ||
                    recipe.title.lowercase().contains(q) ||
                    recipe.cuisine.lowercase().contains(q) ||
                    recipe.tags.any { it.lowercase().contains(q) } ||
                    recipe.ingredients.any { it.name.lowercase().contains(q) }

            val matchesCuisine = cuisineFilter.isNullOrBlank() || cuisineFilter.equals("All", ignoreCase = true) ||
                    recipe.cuisine.equals(cuisineFilter, ignoreCase = true)

            val matchesMealType = mealTypeFilter == null || recipe.mealTypes.contains(mealTypeFilter)

            val matchesTime = maxCookingTime == null || recipe.totalTimeMinutes <= maxCookingTime

            val matchesDiet = dietaryFilters.isEmpty() || dietaryFilters.all { diet ->
                when (diet.lowercase()) {
                    "vegetarian" -> recipe.isVegetarian
                    "vegan" -> recipe.isVegan
                    "high protein" -> recipe.isHighProtein
                    "low carb" -> recipe.isLowCarb
                    "gluten free" -> recipe.isGlutenFree
                    "keto" -> recipe.isKeto
                    else -> true
                }
            }

            matchesQuery && matchesCuisine && matchesMealType && matchesTime && matchesDiet
        }
    }

    // Favorites
    fun getFavoriteIds(): Flow<List<String>> =
        database.favoritesDao().getAllFavorites().map { favList -> favList.map { it.recipeId } }

    fun isFavorite(recipeId: String): Flow<Boolean> =
        database.favoritesDao().isFavorite(recipeId)

    suspend fun toggleFavorite(recipeId: String, currentIsFav: Boolean) {
        if (currentIsFav) {
            database.favoritesDao().removeFavorite(recipeId)
        } else {
            database.favoritesDao().addFavorite(FavoriteEntity(recipeId = recipeId))
        }
    }

    // Cooking History
    fun getCookingHistory(): Flow<List<CookingHistoryEntity>> =
        database.cookingHistoryDao().getHistory()

    fun getCookedCount(): Flow<Int> =
        database.cookingHistoryDao().getCookedCount()

    suspend fun recordCookedMeal(
        recipe: Recipe,
        rating: Int,
        wouldCookAgain: Boolean,
        notes: String
    ) {
        database.cookingHistoryDao().insertRecord(
            CookingHistoryEntity(
                recipeId = recipe.id,
                recipeTitle = recipe.title,
                recipeImageUrl = recipe.imageUrl,
                cookedDateEpochMs = System.currentTimeMillis(),
                rating = rating,
                wouldCookAgain = wouldCookAgain,
                notes = notes
            )
        )
    }
}

class PantryRepository(
    private val database: AppDatabase
) {
    val activePantryItems: Flow<List<PantryEntity>> =
        database.pantryDao().getActivePantryItems()

    val expiringSoonItems: Flow<List<PantryEntity>> =
        database.pantryDao().getExpiringSoonItems()

    suspend fun addItem(name: String, quantity: Float, unit: String, category: String, daysRemaining: Int = 7): Long {
        return database.pantryDao().insertOrUpdate(
            PantryEntity(
                name = name.trim(),
                quantity = quantity,
                unit = unit,
                category = category,
                expiryDaysRemaining = daysRemaining
            )
        )
    }

    suspend fun updateItem(item: PantryEntity) {
        database.pantryDao().update(item)
    }

    suspend fun deleteItem(item: PantryEntity) {
        database.pantryDao().delete(item)
    }

    suspend fun deleteById(id: Long) {
        database.pantryDao().deleteById(id)
    }

    suspend fun seedDefaultPantryIfEmpty() {
        val current = database.pantryDao().getActivePantryItems().firstOrNull()
        if (current.isNullOrEmpty()) {
            val starter = listOf(
                PantryEntity(name = "Chicken Breast", quantity = 500f, unit = "g", category = "Meat & Poultry", expiryDaysRemaining = 2),
                PantryEntity(name = "Tomato", quantity = 4f, unit = "pcs", category = "Vegetables", expiryDaysRemaining = 3),
                PantryEntity(name = "Onion", quantity = 3f, unit = "pcs", category = "Vegetables", expiryDaysRemaining = 12),
                PantryEntity(name = "Garlic", quantity = 8f, unit = "cloves", category = "Vegetables", expiryDaysRemaining = 14),
                PantryEntity(name = "Basmati Rice", quantity = 1000f, unit = "g", category = "Grains & Rice", expiryDaysRemaining = 90),
                PantryEntity(name = "Eggs", quantity = 6f, unit = "pcs", category = "Dairy & Eggs", expiryDaysRemaining = 7),
                PantryEntity(name = "Spinach", quantity = 150f, unit = "g", category = "Vegetables", expiryDaysRemaining = 2),
                PantryEntity(name = "Olive Oil", quantity = 500f, unit = "ml", category = "Sauces & Oils", expiryDaysRemaining = 120),
                PantryEntity(name = "Cheddar Cheese", quantity = 200f, unit = "g", category = "Dairy & Eggs", expiryDaysRemaining = 8),
                PantryEntity(name = "Butter", quantity = 250f, unit = "g", category = "Dairy & Eggs", expiryDaysRemaining = 20),
                PantryEntity(name = "Spaghetti", quantity = 500f, unit = "g", category = "Pasta & Noodles", expiryDaysRemaining = 180),
                PantryEntity(name = "Potato", quantity = 5f, unit = "pcs", category = "Vegetables", expiryDaysRemaining = 15)
            )
            database.pantryDao().insertAll(starter)
        }
    }
}

class MealPlanRepository(
    private val database: AppDatabase,
    private val recipeRepository: RecipeRepository
) {
    val weeklyPlan: Flow<List<MealPlanEntity>> = database.mealPlanDao().getWeeklyPlan()

    suspend fun addMeal(
        dayOfWeek: String,
        mealType: String,
        recipe: Recipe,
        servings: Int = 2
    ) {
        database.mealPlanDao().removeMealSlot(dayOfWeek, mealType)
        database.mealPlanDao().insertMeal(
            MealPlanEntity(
                dayOfWeek = dayOfWeek,
                mealType = mealType,
                recipeId = recipe.id,
                recipeTitle = recipe.title,
                recipeImageUrl = recipe.imageUrl,
                prepTimeMinutes = recipe.totalTimeMinutes,
                servings = servings
            )
        )
    }

    suspend fun removeMeal(meal: MealPlanEntity) {
        database.mealPlanDao().deleteMeal(meal)
    }

    suspend fun clearPlan() {
        database.mealPlanDao().clearAll()
    }

    /**
     * Auto generates a balanced weekly meal plan based on preferences.
     */
    suspend fun generateBalancedWeek(
        recipes: List<Recipe>,
        userAllergies: List<String> = emptyList(),
        dietaryPreferences: List<String> = emptyList()
    ) {
        database.mealPlanDao().clearAll()
        val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val safeRecipes = recipes.filter { RecipeMatchingEngine.isSafeForUser(it, userAllergies, dietaryPreferences) }
        if (safeRecipes.isEmpty()) return

        val breakfasts = safeRecipes.filter { it.mealTypes.contains(MealType.BREAKFAST) }.ifEmpty { safeRecipes }
        val lunches = safeRecipes.filter { it.mealTypes.contains(MealType.LUNCH) }.ifEmpty { safeRecipes }
        val dinners = safeRecipes.filter { it.mealTypes.contains(MealType.DINNER) }.ifEmpty { safeRecipes }

        val plans = mutableListOf<MealPlanEntity>()
        days.forEachIndexed { index, day ->
            val b = breakfasts[index % breakfasts.size]
            val l = lunches[(index + 1) % lunches.size]
            val d = dinners[(index + 2) % dinners.size]

            plans.add(MealPlanEntity(dayOfWeek = day, mealType = "Breakfast", recipeId = b.id, recipeTitle = b.title, recipeImageUrl = b.imageUrl, prepTimeMinutes = b.totalTimeMinutes))
            plans.add(MealPlanEntity(dayOfWeek = day, mealType = "Lunch", recipeId = l.id, recipeTitle = l.title, recipeImageUrl = l.imageUrl, prepTimeMinutes = l.totalTimeMinutes))
            plans.add(MealPlanEntity(dayOfWeek = day, mealType = "Dinner", recipeId = d.id, recipeTitle = d.title, recipeImageUrl = d.imageUrl, prepTimeMinutes = d.totalTimeMinutes))
        }

        database.mealPlanDao().insertAll(plans)
    }
}

class ShoppingRepository(
    private val database: AppDatabase,
    private val pantryDao: com.example.data.local.PantryDao
) {
    val items: Flow<List<ShoppingItemEntity>> = database.shoppingDao().getAllItems()

    suspend fun addItem(
        name: String,
        quantity: Float,
        unit: String,
        category: String,
        sourceRecipe: String? = null
    ) {
        database.shoppingDao().insertItem(
            ShoppingItemEntity(
                name = name.trim(),
                quantity = quantity,
                unit = unit,
                category = category,
                isChecked = false,
                sourceRecipe = sourceRecipe
            )
        )
    }

    suspend fun toggleChecked(id: Long, current: Boolean) {
        database.shoppingDao().setChecked(id, !current)
    }

    suspend fun toggleItem(item: ShoppingItemEntity) {
        database.shoppingDao().setChecked(item.id, !item.isChecked)
    }

    suspend fun deleteItem(item: ShoppingItemEntity) {
        database.shoppingDao().deleteItem(item)
    }

    suspend fun clearChecked() {
        database.shoppingDao().clearCheckedItems()
    }

    suspend fun clearCheckedItems() {
        database.shoppingDao().clearCheckedItems()
    }

    suspend fun clearAll() {
        database.shoppingDao().clearAll()
    }

    suspend fun transferCheckedToPantry(pantryRepository: PantryRepository) {
        val checkedList = database.shoppingDao().getAllItems().firstOrNull()?.filter { it.isChecked } ?: emptyList()
        for (item in checkedList) {
            pantryRepository.addItem(
                name = item.name,
                quantity = item.quantity,
                unit = item.unit,
                category = item.category,
                daysRemaining = 7
            )
        }
        database.shoppingDao().clearCheckedItems()
    }

    /**
     * Shopping List Intelligence:
     * Compares required recipe ingredients against current pantry inventory!
     * e.g., if recipe requires 500g chicken and pantry has 300g, adds 200g.
     */
    suspend fun addMissingIngredientsWithPantryIntelligence(
        missing: List<RecipeIngredient>,
        recipeTitle: String
    ) {
        val activePantry = pantryDao.getActivePantryItems().firstOrNull() ?: emptyList()
        val toInsert = mutableListOf<ShoppingItemEntity>()

        for (item in missing) {
            val existingPantryMatch = activePantry.find {
                RecipeMatchingEngine.matchesIngredient(it.name, item)
            }

            val finalQuantity = if (existingPantryMatch != null && existingPantryMatch.unit.equals(item.unit, ignoreCase = true)) {
                val neededDiff = item.amount - existingPantryMatch.quantity
                if (neededDiff > 0) neededDiff else item.amount
            } else {
                item.amount
            }

            toInsert.add(
                ShoppingItemEntity(
                    name = item.name,
                    quantity = finalQuantity,
                    unit = item.unit,
                    category = "Recipe: $recipeTitle",
                    isChecked = false,
                    sourceRecipe = recipeTitle
                )
            )
        }

        if (toInsert.isNotEmpty()) {
            database.shoppingDao().insertAll(toInsert)
        }
    }
}

class UserPreferencesRepository(
    private val database: AppDatabase
) {
    val preferences: Flow<UserPreferencesEntity?> =
        database.userPreferencesDao().getPreferences()

    suspend fun savePreferences(
        userName: String,
        dietaryPreferences: List<String>,
        allergies: List<String>,
        cookingSkill: String,
        preferredTimeMinutes: Int,
        onboarded: Boolean
    ) {
        database.userPreferencesDao().savePreferences(
            UserPreferencesEntity(
                id = 1,
                userName = userName,
                dietaryPreferencesCsv = dietaryPreferences.joinToString(","),
                allergiesCsv = allergies.joinToString(","),
                cookingSkill = cookingSkill,
                preferredTimeMinutes = preferredTimeMinutes,
                isOnboarded = onboarded
            )
        )
    }

    suspend fun updateUserName(userName: String) {
        database.userPreferencesDao().updateUserName(userName)
    }
}
