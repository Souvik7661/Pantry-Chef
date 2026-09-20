package com.example

import com.example.data.engine.RecipeMatchingEngine
import com.example.data.local.PantryEntity
import com.example.data.model.DifficultyLevel
import com.example.data.model.MealType
import com.example.data.model.NutritionInfo
import com.example.data.model.Recipe
import com.example.data.model.RecipeIngredient
import com.example.data.model.RecipeStep
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RecipeMatchingEngineTest {

    private val sampleRecipe = Recipe(
        id = "test_1",
        title = "Chicken Garlic Rice",
        description = "Tasty meal",
        ingredients = listOf(
            RecipeIngredient("1", "Chicken", 500f, "g"),
            RecipeIngredient("2", "Garlic", 4f, "cloves"),
            RecipeIngredient("3", "Rice", 200f, "g"),
            RecipeIngredient("4", "Olive Oil", 15f, "ml"),
            RecipeIngredient("5", "Soy Sauce", 10f, "ml", optional = true)
        ),
        steps = listOf(RecipeStep(1, "Cook chicken")),
        prepTimeMinutes = 10,
        cookTimeMinutes = 20,
        servings = 2,
        difficulty = DifficultyLevel.EASY,
        cuisine = "Asian",
        mealTypes = listOf(MealType.DINNER),
        nutrition = NutritionInfo(450, 35, 40, 12),
        imageUrl = "",
        tags = listOf("Quick"),
        isGlutenFree = false
    )

    @Test
    fun testExactMatchCalculation() {
        val userIngredients = listOf("Chicken", "Garlic", "Rice", "Olive Oil")
        val match = RecipeMatchingEngine.calculateMatch(sampleRecipe, userIngredients)

        // All required non-optional ingredients are matched (4/4 = 100%)
        assertEquals(100, match.matchPercentage)
        assertEquals(4, match.matchedIngredients.size)
        assertTrue(match.missingIngredients.isEmpty())
    }

    @Test
    fun testPartialMatchCalculation() {
        val userIngredients = listOf("Chicken", "Rice")
        val match = RecipeMatchingEngine.calculateMatch(sampleRecipe, userIngredients)

        // 2 of 4 required are matched -> 50%
        assertEquals(50, match.matchPercentage)
        assertEquals(2, match.matchedIngredients.size)
        assertEquals(2, match.missingIngredients.size)
    }

    @Test
    fun testKitchenRescue() {
        val pantry = listOf(
            PantryEntity(name = "Chicken", quantity = 500f, unit = "g", category = "Meat", expiryDaysRemaining = 1),
            PantryEntity(name = "Garlic", quantity = 4f, unit = "cloves", category = "Produce", expiryDaysRemaining = 2)
        )

        val rescueResult = RecipeMatchingEngine.kitchenRescue(
            recipes = listOf(sampleRecipe),
            pantryItems = pantry
        )

        assertNotNull(rescueResult)
        assertTrue(rescueResult!!.usesExpiringIngredients)
    }
}
