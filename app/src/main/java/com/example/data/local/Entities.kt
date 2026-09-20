package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pantry_items")
data class PantryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val quantity: Float,
    val unit: String,
    val category: String,
    val expiryDaysRemaining: Int = 5,
    val isFinished: Boolean = false,
    val addedDateEpochMs: Long = System.currentTimeMillis()
)

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val recipeId: String,
    val collectionName: String = "All Favorites",
    val dateAddedEpochMs: Long = System.currentTimeMillis()
)

@Entity(tableName = "meal_plan")
data class MealPlanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dayOfWeek: String, // "Monday", "Tuesday", etc.
    val mealType: String,  // "Breakfast", "Lunch", "Dinner", "Snack"
    val recipeId: String,
    val recipeTitle: String,
    val recipeImageUrl: String,
    val prepTimeMinutes: Int = 20,
    val servings: Int = 2
)

@Entity(tableName = "shopping_items")
data class ShoppingItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val quantity: Float,
    val unit: String,
    val category: String,
    val isChecked: Boolean = false,
    val sourceRecipe: String? = null
) {
    val aisleCategory: String get() = category
    val recipeOrigin: String get() = sourceRecipe ?: ""
}

@Entity(tableName = "cooking_history")
data class CookingHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val recipeId: String,
    val recipeTitle: String,
    val recipeImageUrl: String,
    val cookedDateEpochMs: Long = System.currentTimeMillis(),
    val rating: Int = 5,
    val wouldCookAgain: Boolean = true,
    val notes: String = ""
) {
    val cookedDateTimestamp: Long get() = cookedDateEpochMs
    val userRating: Int get() = rating
}

@Entity(tableName = "user_preferences")
data class UserPreferencesEntity(
    @PrimaryKey
    val id: Int = 1,
    val userName: String = "Home Chef",
    val dietaryPreferencesCsv: String = "", // comma-separated
    val allergiesCsv: String = "",          // comma-separated
    val cookingSkill: String = "Intermediate", // Beginner, Intermediate, Advanced
    val preferredTimeMinutes: Int = 30,     // 15, 30, 60, 90
    val isOnboarded: Boolean = false
) {
    val hasCompletedOnboarding: Boolean get() = isOnboarded
    val dietaryPreferences: List<String> get() = if (dietaryPreferencesCsv.isBlank()) emptyList() else dietaryPreferencesCsv.split(",").map { it.trim() }
    val allergies: List<String> get() = if (allergiesCsv.isBlank()) emptyList() else allergiesCsv.split(",").map { it.trim() }
    val cookingSkillLevel: String get() = cookingSkill
    val preferredCookingTimeMinutes: Int get() = preferredTimeMinutes
}
