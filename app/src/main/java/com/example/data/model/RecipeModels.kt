package com.example.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class Ingredient(
    val id: String,
    val name: String,
    val category: IngredientCategory,
    val defaultUnit: String = "pcs",
    val caloriesPer100g: Int = 50,
    val iconName: String = "food"
)

enum class IngredientCategory(val displayName: String) {
    VEGETABLES("Vegetables"),
    FRUITS("Fruits"),
    MEAT("Meat & Poultry"),
    SEAFOOD("Seafood"),
    DAIRY("Dairy & Eggs"),
    GRAINS("Grains & Rice"),
    PASTA("Pasta & Noodles"),
    SPICES("Spices & Herbs"),
    SAUCES("Sauces & Oils"),
    PANTRY("Pantry Staples"),
    SNACKS("Snacks"),
    BEVERAGES("Beverages")
}

@Immutable
data class RecipeIngredient(
    val ingredientId: String,
    val name: String,
    val amount: Float,
    val unit: String,
    val optional: Boolean = false,
    val substitutions: List<String> = emptyList()
)

@Immutable
data class RecipeStep(
    val stepNumber: Int,
    val instruction: String,
    val timerSeconds: Int? = null,
    val tip: String? = null
)

@Immutable
data class NutritionInfo(
    val calories: Int,
    val proteinGrams: Int,
    val carbsGrams: Int,
    val fatGrams: Int,
    val fiberGrams: Int = 3,
    val sugarGrams: Int = 4,
    val sodiumMg: Int = 450
)

enum class DifficultyLevel(val displayName: String) {
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard")
}

enum class MealType(val displayName: String) {
    BREAKFAST("Breakfast"),
    BRUNCH("Brunch"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACK("Snack"),
    DESSERT("Dessert")
}

@Immutable
data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val cuisine: String,
    val mealTypes: List<MealType>,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val servings: Int,
    val difficulty: DifficultyLevel,
    val ingredients: List<RecipeIngredient>,
    val steps: List<RecipeStep>,
    val nutrition: NutritionInfo,
    val tags: List<String> = emptyList(),
    val isVegetarian: Boolean = false,
    val isVegan: Boolean = false,
    val isHighProtein: Boolean = false,
    val isLowCarb: Boolean = false,
    val isGlutenFree: Boolean = false,
    val isDairyFree: Boolean = false,
    val isKeto: Boolean = false,
    val allergens: List<String> = emptyList(),
    val rating: Float = 4.8f,
    val reviewsCount: Int = 128
) {
    val totalTimeMinutes: Int get() = prepTimeMinutes + cookTimeMinutes
}

@Immutable
data class RecipeMatchResult(
    val recipe: Recipe,
    val matchPercentage: Int,
    val matchedIngredients: List<RecipeIngredient>,
    val missingIngredients: List<RecipeIngredient>,
    val usesExpiringIngredients: Boolean = false,
    val expiringIngredientsUsed: List<String> = emptyList()
)

@Immutable
data class IngredientSubstitution(
    val originalIngredient: String,
    val substituteName: String,
    val ratio: String,
    val impactOnFlavor: String,
    val impactOnTexture: String,
    val note: String
)
