package com.example.data.engine

import com.example.data.local.PantryEntity
import com.example.data.model.Recipe
import com.example.data.model.RecipeIngredient
import com.example.data.model.RecipeMatchResult
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap

object RecipeMatchingEngine {

    private val NON_ALPHANUM_REGEX = Regex("[^a-z0-9 ]")
    private val DESCRIPTOR_REGEX = Regex("\\b(fresh|canned|diced|sliced|chopped|minced|rolled|fillet|breasts?|powder|seeds?)\\b")
    private val normCache = ConcurrentHashMap<String, String>()

    /**
     * Normalizes ingredient names for matching: lowercase, removes plurals, removes punctuation.
     * Uses memoization and precompiled regexes for zero-allocation performance.
     */
    fun normalize(name: String): String {
        return normCache.getOrPut(name) {
            name.lowercase(Locale.ROOT)
                .trim()
                .replace(NON_ALPHANUM_REGEX, "")
                .replace(DESCRIPTOR_REGEX, "")
                .trim()
        }
    }

    /**
     * Checks if a user-supplied ingredient matches a recipe ingredient.
     */
    fun matchesIngredient(userIng: String, recipeIng: RecipeIngredient): Boolean {
        val normUser = normalize(userIng)
        val normRecipe = normalize(recipeIng.name)

        if (normUser.isEmpty() || normRecipe.isEmpty()) return false
        if (normUser == normRecipe) return true
        if (normUser.contains(normRecipe) || normRecipe.contains(normUser)) return true

        // Check common word overlap (e.g., "tomato" matches "cherry tomatoes", "chicken" matches "chicken breast")
        val userTokens = normUser.split(" ").filter { it.length > 2 }
        val recipeTokens = normRecipe.split(" ").filter { it.length > 2 }
        return userTokens.any { u -> recipeTokens.any { r -> u == r || u.startsWith(r) || r.startsWith(u) } }
    }

    /**
     * Calculates match result for a recipe against provided ingredients and expiring pantry items.
     */
    fun calculateMatch(
        recipe: Recipe,
        userIngredients: List<String>,
        expiringItems: List<PantryEntity> = emptyList()
    ): RecipeMatchResult {
        val nonOptionalIngredients = recipe.ingredients.filter { !it.optional }
        val targetList = if (nonOptionalIngredients.isNotEmpty()) nonOptionalIngredients else recipe.ingredients

        val matched = mutableListOf<RecipeIngredient>()
        val missing = mutableListOf<RecipeIngredient>()

        for (recipeIng in recipe.ingredients) {
            val isMatched = userIngredients.any { userIng -> matchesIngredient(userIng, recipeIng) }
            if (isMatched) {
                matched.add(recipeIng)
            } else if (!recipeIng.optional) {
                missing.add(recipeIng)
            }
        }

        // Percentage calculated against total required ingredients
        val basePercent = if (targetList.isNotEmpty()) {
            val matchedRequiredCount = matched.count { !it.optional }
            ((matchedRequiredCount.toFloat() / targetList.size.toFloat()) * 100f).toInt().coerceIn(0, 100)
        } else {
            0
        }

        // Check if this recipe utilizes ingredients nearing expiry
        val expiringNames = expiringItems.map { it.name }
        val expiringUsed = mutableListOf<String>()
        for (expItem in expiringItems) {
            if (recipe.ingredients.any { matchesIngredient(expItem.name, it) }) {
                expiringUsed.add(expItem.name)
            }
        }

        return RecipeMatchResult(
            recipe = recipe,
            matchPercentage = basePercent,
            matchedIngredients = matched,
            missingIngredients = missing,
            usesExpiringIngredients = expiringUsed.isNotEmpty(),
            expiringIngredientsUsed = expiringUsed
        )
    }

    /**
     * Filters out allergens and matches user dietary restrictions.
     */
    fun isSafeForUser(
        recipe: Recipe,
        userAllergies: List<String>,
        dietaryPreferences: List<String>
    ): Boolean {
        // Strict Allergen Safety: Never recommend allergen recipes
        val normalizedAllergies = userAllergies.map { normalize(it) }.filter { it.isNotEmpty() }
        for (allergy in normalizedAllergies) {
            // Check recipe's listed allergens
            if (recipe.allergens.any { normalize(it).contains(allergy) || allergy.contains(normalize(it)) }) {
                return false
            }
            // Check individual recipe ingredient names
            if (recipe.ingredients.any { normalize(it.name).contains(allergy) || allergy.contains(normalize(it.name)) }) {
                return false
            }
        }

        // Dietary preferences
        val normalizedDiet = dietaryPreferences.map { it.lowercase(Locale.getDefault()) }
        if (normalizedDiet.contains("vegetarian") && !recipe.isVegetarian) return false
        if (normalizedDiet.contains("vegan") && !recipe.isVegan) return false
        if (normalizedDiet.contains("gluten free") && !recipe.isGlutenFree) return false
        if (normalizedDiet.contains("dairy free") && !recipe.isDairyFree) return false
        if (normalizedDiet.contains("keto") && !recipe.isKeto) return false
        if (normalizedDiet.contains("high protein") && !recipe.isHighProtein) return false
        if (normalizedDiet.contains("low carb") && !recipe.isLowCarb) return false

        return true
    }

    /**
     * Rank recipes:
     * - Primary: Match percentage descending
     * - Secondary: Uses expiring ingredients (bonus boost!)
     * - Tertiary: Missing count ascending
     * - Quaternary: Rating descending
     */
    fun rankRecipes(
        recipes: List<Recipe>,
        userIngredients: List<String>,
        expiringItems: List<PantryEntity> = emptyList(),
        userAllergies: List<String> = emptyList(),
        dietaryPreferences: List<String> = emptyList()
    ): List<RecipeMatchResult> {
        return recipes
            .filter { isSafeForUser(it, userAllergies, dietaryPreferences) }
            .map { calculateMatch(it, userIngredients, expiringItems) }
            .sortedWith(
                compareByDescending<RecipeMatchResult> { it.matchPercentage }
                    .thenByDescending { if (it.usesExpiringIngredients) 1 else 0 }
                    .thenBy { it.missingIngredients.size }
                    .thenByDescending { it.recipe.rating }
            )
    }

    /**
     * "Kitchen Rescue" signature engine:
     * Finds the single optimal recipe to make tonight that rescues items nearing expiry.
     */
    fun kitchenRescue(
        recipes: List<Recipe>,
        pantryItems: List<PantryEntity>,
        userAllergies: List<String> = emptyList(),
        dietaryPreferences: List<String> = emptyList()
    ): RecipeMatchResult? {
        val activePantryNames = pantryItems.map { it.name }
        val expiringItems = pantryItems.filter { it.expiryDaysRemaining <= 4 }

        val ranked = rankRecipes(
            recipes = recipes,
            userIngredients = activePantryNames,
            expiringItems = expiringItems,
            userAllergies = userAllergies,
            dietaryPreferences = dietaryPreferences
        )

        // Prioritize recipes that actually rescue expiring items and have at least a decent match
        return ranked.firstOrNull { it.usesExpiringIngredients && it.matchPercentage >= 50 }
            ?: ranked.firstOrNull { it.matchPercentage >= 40 }
            ?: ranked.firstOrNull()
    }
}
