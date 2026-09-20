package com.example.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Discover : Screen("discover")
    object MealPlan : Screen("meal_plan")
    object Shopping : Screen("shopping")
    object Profile : Screen("profile")
    object Pantry : Screen("pantry")
    object Results : Screen("results")
    object AiGenerator : Screen("ai_generator")
    object RecipeDetail : Screen("recipe_detail/{recipeId}") {
        fun createRoute(recipeId: String) = "recipe_detail/$recipeId"
    }
    object CookingMode : Screen("cooking_mode/{recipeId}") {
        fun createRoute(recipeId: String) = "cooking_mode/$recipeId"
    }
}
