package com.example.data.sample

import com.example.data.model.DifficultyLevel
import com.example.data.model.Ingredient
import com.example.data.model.IngredientCategory
import com.example.data.model.IngredientSubstitution
import com.example.data.model.MealType
import com.example.data.model.NutritionInfo
import com.example.data.model.Recipe
import com.example.data.model.RecipeIngredient
import com.example.data.model.RecipeStep

object SeedData {

    val commonAllergies = listOf(
        "Peanuts", "Tree nuts", "Milk", "Eggs", "Soy", "Wheat", "Fish", "Shellfish", "Sesame"
    )

    val dietaryPreferencesList = listOf(
        "No preference", "Vegetarian", "Vegan", "Pescatarian", "High protein",
        "Low carb", "Gluten free", "Dairy free", "Keto", "Halal"
    )

    val starterPantry = listOf(
        "Rice", "Eggs", "Chicken", "Potato", "Tomato", "Onion",
        "Garlic", "Bread", "Pasta", "Cheese", "Milk", "Spinach"
    )

    val ingredientsList: List<Ingredient> = listOf(
        // Vegetables
        Ingredient("ing_tomato", "Tomato", IngredientCategory.VEGETABLES, "pcs", 18),
        Ingredient("ing_onion", "Onion", IngredientCategory.VEGETABLES, "pcs", 40),
        Ingredient("ing_garlic", "Garlic", IngredientCategory.VEGETABLES, "cloves", 149),
        Ingredient("ing_potato", "Potato", IngredientCategory.VEGETABLES, "pcs", 77),
        Ingredient("ing_spinach", "Spinach", IngredientCategory.VEGETABLES, "g", 23),
        Ingredient("ing_bell_pepper", "Bell Pepper", IngredientCategory.VEGETABLES, "pcs", 31),
        Ingredient("ing_carrot", "Carrot", IngredientCategory.VEGETABLES, "pcs", 41),
        Ingredient("ing_broccoli", "Broccoli", IngredientCategory.VEGETABLES, "g", 34),
        Ingredient("ing_mushroom", "Mushroom", IngredientCategory.VEGETABLES, "g", 22),
        Ingredient("ing_cucumber", "Cucumber", IngredientCategory.VEGETABLES, "pcs", 15),
        Ingredient("ing_zucchini", "Zucchini", IngredientCategory.VEGETABLES, "pcs", 17),
        Ingredient("ing_avocado", "Avocado", IngredientCategory.VEGETABLES, "pcs", 160),
        Ingredient("ing_green_chili", "Green Chili", IngredientCategory.VEGETABLES, "pcs", 40),
        Ingredient("ing_ginger", "Ginger", IngredientCategory.VEGETABLES, "g", 80),

        // Fruits
        Ingredient("ing_lemon", "Lemon", IngredientCategory.FRUITS, "pcs", 29),
        Ingredient("ing_lime", "Lime", IngredientCategory.FRUITS, "pcs", 30),
        Ingredient("ing_apple", "Apple", IngredientCategory.FRUITS, "pcs", 52),
        Ingredient("ing_banana", "Banana", IngredientCategory.FRUITS, "pcs", 89),

        // Meat & Poultry
        Ingredient("ing_chicken_breast", "Chicken Breast", IngredientCategory.MEAT, "g", 165),
        Ingredient("ing_chicken_thighs", "Chicken Thighs", IngredientCategory.MEAT, "g", 209),
        Ingredient("ing_ground_beef", "Ground Beef", IngredientCategory.MEAT, "g", 250),
        Ingredient("ing_pork_chop", "Pork Chop", IngredientCategory.MEAT, "g", 231),
        Ingredient("ing_bacon", "Bacon", IngredientCategory.MEAT, "slices", 541),

        // Seafood
        Ingredient("ing_salmon_fillet", "Salmon Fillet", IngredientCategory.SEAFOOD, "g", 208),
        Ingredient("ing_shrimp", "Shrimp", IngredientCategory.SEAFOOD, "g", 99),
        Ingredient("ing_tuna_canned", "Canned Tuna", IngredientCategory.SEAFOOD, "can", 132),

        // Dairy & Eggs
        Ingredient("ing_eggs", "Eggs", IngredientCategory.DAIRY, "pcs", 155),
        Ingredient("ing_milk", "Milk", IngredientCategory.DAIRY, "ml", 42),
        Ingredient("ing_cheese_cheddar", "Cheddar Cheese", IngredientCategory.DAIRY, "g", 402),
        Ingredient("ing_cheese_parmesan", "Parmesan Cheese", IngredientCategory.DAIRY, "g", 431),
        Ingredient("ing_cheese_mozzarella", "Mozzarella Cheese", IngredientCategory.DAIRY, "g", 280),
        Ingredient("ing_heavy_cream", "Heavy Cream", IngredientCategory.DAIRY, "ml", 345),
        Ingredient("ing_butter", "Butter", IngredientCategory.DAIRY, "g", 717),
        Ingredient("ing_greek_yogurt", "Greek Yogurt", IngredientCategory.DAIRY, "g", 59),
        Ingredient("ing_paneer", "Paneer", IngredientCategory.DAIRY, "g", 265),

        // Grains & Rice
        Ingredient("ing_rice_basmati", "Basmati Rice", IngredientCategory.GRAINS, "g", 130),
        Ingredient("ing_brown_rice", "Brown Rice", IngredientCategory.GRAINS, "g", 111),
        Ingredient("ing_quinoa", "Quinoa", IngredientCategory.GRAINS, "g", 120),
        Ingredient("ing_oats", "Rolled Oats", IngredientCategory.GRAINS, "g", 389),
        Ingredient("ing_bread", "Bread", IngredientCategory.GRAINS, "slices", 265),
        Ingredient("ing_tortilla", "Tortilla", IngredientCategory.GRAINS, "pcs", 218),

        // Pasta & Noodles
        Ingredient("ing_spaghetti", "Spaghetti", IngredientCategory.PASTA, "g", 158),
        Ingredient("ing_penne", "Penne Pasta", IngredientCategory.PASTA, "g", 158),
        Ingredient("ing_egg_noodles", "Egg Noodles", IngredientCategory.PASTA, "g", 138),

        // Spices & Herbs
        Ingredient("ing_black_pepper", "Black Pepper", IngredientCategory.SPICES, "tsp", 251),
        Ingredient("ing_salt", "Salt", IngredientCategory.SPICES, "tsp", 0),
        Ingredient("ing_cumin", "Cumin Seeds / Powder", IngredientCategory.SPICES, "tsp", 375),
        Ingredient("ing_turmeric", "Turmeric Powder", IngredientCategory.SPICES, "tsp", 354),
        Ingredient("ing_paprika", "Smoked Paprika", IngredientCategory.SPICES, "tsp", 282),
        Ingredient("ing_garam_masala", "Garam Masala", IngredientCategory.SPICES, "tsp", 379),
        Ingredient("ing_chili_flakes", "Red Chili Flakes", IngredientCategory.SPICES, "tsp", 318),
        Ingredient("ing_oregano", "Dried Oregano", IngredientCategory.SPICES, "tsp", 265),
        Ingredient("ing_basil_fresh", "Fresh Basil", IngredientCategory.SPICES, "leaves", 23),
        Ingredient("ing_cilantro", "Fresh Cilantro", IngredientCategory.SPICES, "sprigs", 23),

        // Sauces & Oils
        Ingredient("ing_olive_oil", "Olive Oil", IngredientCategory.SAUCES, "tbsp", 884),
        Ingredient("ing_vegetable_oil", "Vegetable Oil", IngredientCategory.SAUCES, "tbsp", 884),
        Ingredient("ing_soy_sauce", "Soy Sauce", IngredientCategory.SAUCES, "tbsp", 53),
        Ingredient("ing_sesame_oil", "Sesame Oil", IngredientCategory.SAUCES, "tsp", 884),
        Ingredient("ing_tomato_paste", "Tomato Paste", IngredientCategory.SAUCES, "tbsp", 82),
        Ingredient("ing_mayonnaise", "Mayonnaise", IngredientCategory.SAUCES, "tbsp", 680),
        Ingredient("ing_dijon_mustard", "Dijon Mustard", IngredientCategory.SAUCES, "tsp", 66),
        Ingredient("ing_honey", "Honey", IngredientCategory.SAUCES, "tbsp", 304),

        // Pantry Staples
        Ingredient("ing_flour", "All-Purpose Flour", IngredientCategory.PANTRY, "g", 364),
        Ingredient("ing_sugar", "Sugar", IngredientCategory.PANTRY, "tsp", 387),
        Ingredient("ing_cornstarch", "Cornstarch", IngredientCategory.PANTRY, "tbsp", 381),
        Ingredient("ing_canned_beans", "Black Beans / Chickpeas", IngredientCategory.PANTRY, "can", 91),
        Ingredient("ing_canned_tomatoes", "Diced Canned Tomatoes", IngredientCategory.PANTRY, "can", 32),
        Ingredient("ing_chicken_broth", "Chicken Broth", IngredientCategory.PANTRY, "ml", 15),

        // Added for World Cuisine Expansion
        Ingredient("ing_lettuce", "Lettuce", IngredientCategory.VEGETABLES, "pcs", 15),
        Ingredient("ing_red_onion", "Red Onion", IngredientCategory.VEGETABLES, "pcs", 40),
        Ingredient("ing_eggplant", "Eggplant", IngredientCategory.VEGETABLES, "pcs", 25),
        Ingredient("ing_green_peas", "Green Peas", IngredientCategory.VEGETABLES, "g", 81),
        Ingredient("ing_red_pepper", "Red Bell Pepper", IngredientCategory.VEGETABLES, "pcs", 31),
        Ingredient("ing_mushrooms", "Mushrooms", IngredientCategory.VEGETABLES, "g", 22),
        Ingredient("ing_tomatoes", "Tomatoes", IngredientCategory.VEGETABLES, "g", 18),
        Ingredient("ing_mango", "Ripe Mango", IngredientCategory.FRUITS, "pcs", 60),
        Ingredient("ing_chicken", "Whole Chicken", IngredientCategory.MEAT, "kg", 215),
        Ingredient("ing_beef", "Thinly Sliced Beef", IngredientCategory.MEAT, "g", 250),
        Ingredient("ing_yogurt", "Plain Yogurt", IngredientCategory.DAIRY, "g", 61),
        Ingredient("ing_cheddar", "Cheddar Cheese", IngredientCategory.DAIRY, "slices", 402),
        Ingredient("ing_egg", "Egg", IngredientCategory.DAIRY, "pcs", 155),
        Ingredient("ing_rice", "Cooked Rice", IngredientCategory.GRAINS, "g", 130),
        Ingredient("ing_paella_rice", "Paella Rice", IngredientCategory.GRAINS, "g", 130),
        Ingredient("ing_sticky_rice", "Glutinous Rice", IngredientCategory.GRAINS, "g", 130),
        Ingredient("ing_corn_tortillas", "Corn Tortillas", IngredientCategory.GRAINS, "pcs", 218),
        Ingredient("ing_sourdough", "Sourdough Bread", IngredientCategory.GRAINS, "slices", 265),
        Ingredient("ing_burger_buns", "Burger Buns", IngredientCategory.GRAINS, "pcs", 260),
        Ingredient("ing_pita", "Pita Bread", IngredientCategory.GRAINS, "pcs", 275),
        Ingredient("ing_rice_noodles", "Rice Noodles", IngredientCategory.PASTA, "g", 108),
        Ingredient("ing_red_chili", "Red Chili Flakes", IngredientCategory.SPICES, "tsp", 318),
        Ingredient("ing_dried_chili", "Dried Red Chilies", IngredientCategory.SPICES, "pcs", 318),
        Ingredient("ing_dried_oregano", "Dried Oregano", IngredientCategory.SPICES, "tsp", 265),
        Ingredient("ing_basil", "Thai Basil", IngredientCategory.SPICES, "g", 23),
        Ingredient("ing_parsley", "Fresh Parsley", IngredientCategory.SPICES, "tbsp", 36),
        Ingredient("ing_thyme", "Fresh Thyme", IngredientCategory.SPICES, "tbsp", 101),
        Ingredient("ing_saffron", "Saffron", IngredientCategory.SPICES, "tsp", 310),
        Ingredient("ing_sichuan_peppercorn", "Sichuan Peppercorns", IngredientCategory.SPICES, "tsp", 296),
        Ingredient("ing_star_anise", "Star Anise", IngredientCategory.SPICES, "pcs", 337),
        Ingredient("ing_sesame_seeds", "Sesame Seeds", IngredientCategory.SPICES, "tbsp", 573),
        Ingredient("ing_tomato_puree", "Tomato Puree", IngredientCategory.PANTRY, "g", 38),
        Ingredient("ing_coconut_milk", "Coconut Milk", IngredientCategory.PANTRY, "ml", 230),
        Ingredient("ing_chicken_stock", "Chicken Stock", IngredientCategory.PANTRY, "ml", 15),
        Ingredient("ing_peanuts", "Roasted Peanuts", IngredientCategory.SNACKS, "g", 567),
        Ingredient("ing_tahini", "Tahini", IngredientCategory.SAUCES, "tbsp", 595),
        Ingredient("ing_fish_sauce", "Fish Sauce", IngredientCategory.SAUCES, "tbsp", 35),
        Ingredient("ing_rice_vinegar", "Rice Vinegar", IngredientCategory.SAUCES, "tbsp", 18),
        Ingredient("ing_gochujang", "Gochujang", IngredientCategory.SAUCES, "tbsp", 240),
        Ingredient("ing_green_curry_paste", "Green Curry Paste", IngredientCategory.SAUCES, "tbsp", 120)
    )

    val substitutions: List<IngredientSubstitution> = listOf(
        IngredientSubstitution("Heavy Cream", "Greek yogurt + splash of milk", "1:1", "Slightly tangier", "Equally creamy", "Ideal for sauces and pasta."),
        IngredientSubstitution("Heavy Cream", "Milk + melted butter (3:1 ratio)", "1:1", "Neutral and rich", "Smooth", "Great for simmering dishes."),
        IngredientSubstitution("Butter", "Olive oil", "3:4 (3/4 cup oil per cup butter)", "Light fruitiness", "Moist", "Best for sauteing and pasta."),
        IngredientSubstitution("Chicken Breast", "Tofu or Paneer", "1:1", "Milder", "Firm chew", "Excellent vegetarian protein alternative."),
        IngredientSubstitution("Soy Sauce", "Tamari or Coconut Aminos", "1:1", "Sweeter", "Similar saltiness", "Gluten-free friendly alternative."),
        IngredientSubstitution("Parmesan Cheese", "Nutritional yeast", "1:1", "Cheesy and nutty", "Powdery", "Vegan and dairy-free replacement."),
        IngredientSubstitution("Sour Cream", "Plain Greek yogurt", "1:1", "Very similar", "Rich and creamy", "High protein swap."),
        IngredientSubstitution("All-Purpose Flour", "Cornstarch (for thickening)", "1:2 (half amount of cornstarch)", "Neutral", "Glossy finish", "Ideal for thickening gravies.")
    )

    val recipes: List<Recipe> = listOf(
        Recipe(
            id = "rec_01",
            title = "Creamy Garlic Chicken",
            description = "Pan-seared chicken breast simmered in a velvety garlic and herb cream sauce with fresh spinach.",
            imageUrl = "https://images.unsplash.com/photo-1604908176997-125f25cc6f3d?auto=format&fit=crop&w=800&q=80",
            cuisine = "Italian",
            mealTypes = listOf(MealType.DINNER, MealType.LUNCH),
            prepTimeMinutes = 10,
            cookTimeMinutes = 25,
            servings = 4,
            difficulty = DifficultyLevel.MEDIUM,
            ingredients = listOf(
                RecipeIngredient("ing_chicken_breast", "Chicken Breast", 500f, "g"),
                RecipeIngredient("ing_garlic", "Garlic", 5f, "cloves"),
                RecipeIngredient("ing_spinach", "Spinach", 100f, "g"),
                RecipeIngredient("ing_heavy_cream", "Heavy Cream", 200f, "ml", substitutions = listOf("Milk + butter", "Greek yogurt")),
                RecipeIngredient("ing_cheese_parmesan", "Parmesan Cheese", 50f, "g", optional = true),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 2f, "tbsp"),
                RecipeIngredient("ing_salt", "Salt", 1f, "tsp"),
                RecipeIngredient("ing_black_pepper", "Black Pepper", 1f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Season chicken breasts generously on both sides with salt and black pepper.", tip = "Pat chicken dry before seasoning for a better sear."),
                RecipeStep(2, "Heat 2 tbsp olive oil in a large skillet over medium-high heat.", timerSeconds = 120),
                RecipeStep(3, "Add chicken breasts and sear until golden brown on both sides (about 6-7 mins per side). Transfer to a warm plate.", timerSeconds = 420),
                RecipeStep(4, "In the same pan, reduce heat to medium and sauté minced garlic for 1 minute until fragrant.", timerSeconds = 60),
                RecipeStep(5, "Pour in heavy cream and scrape any browned bits from the pan bottom. Bring to a gentle simmer.", timerSeconds = 180),
                RecipeStep(6, "Stir in parmesan cheese and fresh spinach until wilted.", timerSeconds = 120),
                RecipeStep(7, "Return chicken to the skillet, spoon sauce over the top, and simmer for 3 minutes before serving.", timerSeconds = 180)
            ),
            nutrition = NutritionInfo(calories = 480, proteinGrams = 46, carbsGrams = 6, fatGrams = 30),
            tags = listOf("High Protein", "Low Carb", "Keto", "Quick Dinners"),
            isHighProtein = true,
            isLowCarb = true,
            isKeto = true,
            rating = 4.9f,
            reviewsCount = 342
        ),

        Recipe(
            id = "rec_02",
            title = "Chicken Tomato Rice Skillet",
            description = "A comforting one-pot meal featuring golden chicken, basmati rice, tender onions, and sweet blistered tomatoes.",
            imageUrl = "https://images.unsplash.com/photo-1512058564366-18510be2db19?auto=format&fit=crop&w=800&q=80",
            cuisine = "Mediterranean",
            mealTypes = listOf(MealType.DINNER, MealType.LUNCH),
            prepTimeMinutes = 8,
            cookTimeMinutes = 20,
            servings = 3,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_chicken_breast", "Chicken Breast", 400f, "g"),
                RecipeIngredient("ing_rice_basmati", "Basmati Rice", 200f, "g"),
                RecipeIngredient("ing_tomato", "Tomato", 3f, "pcs"),
                RecipeIngredient("ing_onion", "Onion", 1f, "pcs"),
                RecipeIngredient("ing_garlic", "Garlic", 3f, "cloves"),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 2f, "tbsp"),
                RecipeIngredient("ing_chicken_broth", "Chicken Broth", 400f, "ml"),
                RecipeIngredient("ing_salt", "Salt", 1f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Dice chicken, onion, and fresh tomatoes into bite-sized cubes."),
                RecipeStep(2, "Heat olive oil in a skillet and brown chicken cubes for 4 minutes.", timerSeconds = 240),
                RecipeStep(3, "Add chopped onion and minced garlic, cook for 2 minutes until translucent.", timerSeconds = 120),
                RecipeStep(4, "Add dry basmati rice and toast lightly for 1 minute.", timerSeconds = 60),
                RecipeStep(5, "Pour chicken broth and chopped tomatoes over the rice. Cover and simmer on low for 15 minutes.", timerSeconds = 900),
                RecipeStep(6, "Remove lid, fluff rice with a fork, and let sit for 2 minutes before serving.", timerSeconds = 120)
            ),
            nutrition = NutritionInfo(calories = 420, proteinGrams = 38, carbsGrams = 48, fatGrams = 9),
            tags = listOf("One Pot", "Under 30 Minutes", "High Protein", "Family Favorite"),
            isHighProtein = true,
            rating = 4.8f,
            reviewsCount = 215
        ),

        Recipe(
            id = "rec_03",
            title = "Classic Fluffy French Omelette",
            description = "Silky, tender eggs folded with cheddar cheese, fresh herbs, and butter. The ultimate quick pantry meal.",
            imageUrl = "https://images.unsplash.com/photo-1510693206972-df098062cb71?auto=format&fit=crop&w=800&q=80",
            cuisine = "French",
            mealTypes = listOf(MealType.BREAKFAST, MealType.LUNCH),
            prepTimeMinutes = 3,
            cookTimeMinutes = 7,
            servings = 1,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_eggs", "Eggs", 3f, "pcs"),
                RecipeIngredient("ing_butter", "Butter", 15f, "g"),
                RecipeIngredient("ing_cheese_cheddar", "Cheddar Cheese", 30f, "g", optional = true),
                RecipeIngredient("ing_salt", "Salt", 0.5f, "tsp"),
                RecipeIngredient("ing_black_pepper", "Black Pepper", 0.5f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Whisk eggs with salt and black pepper until completely smooth and aerated.", tip = "Use a fork for 60 seconds to break down egg whites completely."),
                RecipeStep(2, "Melt butter in a non-stick skillet over medium-low heat until frothy.", timerSeconds = 60),
                RecipeStep(3, "Pour in eggs and shake pan continuously while swirling with a rubber spatula for 2 minutes.", timerSeconds = 120),
                RecipeStep(4, "Sprinkle cheese along the center, gently fold edges over, and slide onto warm plate.", timerSeconds = 60)
            ),
            nutrition = NutritionInfo(calories = 310, proteinGrams = 22, carbsGrams = 2, fatGrams = 24),
            tags = listOf("Quick & Easy", "Under 15 Minutes", "Vegetarian", "Keto", "High Protein"),
            isVegetarian = true,
            isHighProtein = true,
            isKeto = true,
            rating = 4.7f,
            reviewsCount = 180
        ),

        Recipe(
            id = "rec_04",
            title = "Quick Garlic Butter Spaghetti",
            description = "A classic Aglio e Olio with al dente spaghetti tossed in golden garlic slices, olive oil, red pepper flakes, and parmesan.",
            imageUrl = "https://images.unsplash.com/photo-1551183053-bf91a1d81141?auto=format&fit=crop&w=800&q=80",
            cuisine = "Italian",
            mealTypes = listOf(MealType.DINNER, MealType.LUNCH),
            prepTimeMinutes = 5,
            cookTimeMinutes = 12,
            servings = 2,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_spaghetti", "Spaghetti", 200f, "g"),
                RecipeIngredient("ing_garlic", "Garlic", 6f, "cloves"),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 3f, "tbsp"),
                RecipeIngredient("ing_chili_flakes", "Red Chili Flakes", 1f, "tsp", optional = true),
                RecipeIngredient("ing_cheese_parmesan", "Parmesan Cheese", 30f, "g", optional = true),
                RecipeIngredient("ing_salt", "Salt", 1f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Boil heavily salted water and cook spaghetti for 8-9 minutes until al dente.", timerSeconds = 540),
                RecipeStep(2, "Meanwhile, thinly slice garlic cloves.", tip = "Slice evenly so the garlic toasts uniformly without burning."),
                RecipeStep(3, "Heat olive oil in a pan over low heat; add garlic and red chili flakes. Sauté gently until pale golden.", timerSeconds = 180),
                RecipeStep(4, "Reserve 1/2 cup pasta water, drain pasta, and toss spaghetti directly into the garlic oil.", timerSeconds = 60),
                RecipeStep(5, "Add a splash of pasta water and vigorously toss until an emulsion coats every strand. Top with parmesan.", timerSeconds = 60)
            ),
            nutrition = NutritionInfo(calories = 490, proteinGrams = 14, carbsGrams = 72, fatGrams = 17),
            tags = listOf("Budget Friendly", "Vegetarian", "Quick & Easy", "Pantry Staples"),
            isVegetarian = true,
            rating = 4.9f,
            reviewsCount = 410
        ),

        Recipe(
            id = "rec_05",
            title = "Aloo Jeera (Spiced Cumin Potatoes)",
            description = "Crispy golden cubed potatoes tempered with roasted cumin seeds, turmeric, green chili, and fresh coriander.",
            imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?auto=format&fit=crop&w=800&q=80",
            cuisine = "Indian",
            mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
            prepTimeMinutes = 10,
            cookTimeMinutes = 15,
            servings = 3,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_potato", "Potato", 4f, "pcs"),
                RecipeIngredient("ing_cumin", "Cumin Seeds / Powder", 2f, "tsp"),
                RecipeIngredient("ing_turmeric", "Turmeric Powder", 0.5f, "tsp"),
                RecipeIngredient("ing_green_chili", "Green Chili", 2f, "pcs", optional = true),
                RecipeIngredient("ing_vegetable_oil", "Vegetable Oil", 2f, "tbsp"),
                RecipeIngredient("ing_salt", "Salt", 1f, "tsp"),
                RecipeIngredient("ing_cilantro", "Fresh Cilantro", 2f, "sprigs", optional = true)
            ),
            steps = listOf(
                RecipeStep(1, "Boil or microwave potatoes until tender, then peel and dice into 1-inch cubes."),
                RecipeStep(2, "Heat vegetable oil in a pan and add cumin seeds; let them crackle and release aroma.", timerSeconds = 45),
                RecipeStep(3, "Add chopped green chili and turmeric powder, stirring quickly for 15 seconds.", timerSeconds = 15),
                RecipeStep(4, "Toss in potato cubes and salt. Sauté on medium-high heat for 8 minutes until crisp edges form.", timerSeconds = 480),
                RecipeStep(5, "Garnish with chopped fresh cilantro and serve hot.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 230, proteinGrams = 5, carbsGrams = 38, fatGrams = 8),
            tags = listOf("Indian Favorites", "Vegetarian", "Vegan", "Budget Friendly", "Gluten Free"),
            isVegetarian = true,
            isVegan = true,
            isGlutenFree = true,
            rating = 4.8f,
            reviewsCount = 190
        ),

        Recipe(
            id = "rec_06",
            title = "Paneer Butter Masala",
            description = "Succulent paneer cubes simmered in a silky tomato, onion, and cashew gravy infused with aromatic spices.",
            imageUrl = "https://images.unsplash.com/photo-1631452180519-c014fe946bc7?auto=format&fit=crop&w=800&q=80",
            cuisine = "Indian",
            mealTypes = listOf(MealType.DINNER, MealType.LUNCH),
            prepTimeMinutes = 12,
            cookTimeMinutes = 20,
            servings = 4,
            difficulty = DifficultyLevel.MEDIUM,
            ingredients = listOf(
                RecipeIngredient("ing_paneer", "Paneer", 300f, "g"),
                RecipeIngredient("ing_tomato", "Tomato", 4f, "pcs"),
                RecipeIngredient("ing_onion", "Onion", 2f, "pcs"),
                RecipeIngredient("ing_garlic", "Garlic", 4f, "cloves"),
                RecipeIngredient("ing_ginger", "Ginger", 15f, "g"),
                RecipeIngredient("ing_butter", "Butter", 25f, "g"),
                RecipeIngredient("ing_heavy_cream", "Heavy Cream", 50f, "ml"),
                RecipeIngredient("ing_garam_masala", "Garam Masala", 1f, "tsp"),
                RecipeIngredient("ing_turmeric", "Turmeric Powder", 0.5f, "tsp"),
                RecipeIngredient("ing_salt", "Salt", 1f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Roughly chop tomatoes and onions. Sauté in 1 tbsp butter with garlic and ginger for 6 minutes.", timerSeconds = 360),
                RecipeStep(2, "Blend the sautéed tomato-onion mixture into a velvety smooth puree."),
                RecipeStep(3, "Heat remaining butter in the pan, pour the sauce through a strainer, and add garam masala, turmeric, and salt.", timerSeconds = 240),
                RecipeStep(4, "Add paneer cubes and simmer gently for 5 minutes.", timerSeconds = 300),
                RecipeStep(5, "Swirl in heavy cream and serve with warm rice or naan.", timerSeconds = 60)
            ),
            nutrition = NutritionInfo(calories = 390, proteinGrams = 18, carbsGrams = 14, fatGrams = 31),
            tags = listOf("Indian Favorites", "Vegetarian", "High Protein", "Weekend Cooking"),
            isVegetarian = true,
            isHighProtein = true,
            rating = 4.9f,
            reviewsCount = 520
        ),

        Recipe(
            id = "rec_07",
            title = "Honey Garlic Glazed Salmon",
            description = "Pan-crisped salmon fillets glazed in a sticky, savory reduction of honey, soy sauce, and fresh garlic.",
            imageUrl = "https://images.unsplash.com/photo-1467003909585-2f8a72700288?auto=format&fit=crop&w=800&q=80",
            cuisine = "Asian",
            mealTypes = listOf(MealType.DINNER),
            prepTimeMinutes = 5,
            cookTimeMinutes = 12,
            servings = 2,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_salmon_fillet", "Salmon Fillet", 350f, "g"),
                RecipeIngredient("ing_honey", "Honey", 2f, "tbsp"),
                RecipeIngredient("ing_soy_sauce", "Soy Sauce", 2f, "tbsp"),
                RecipeIngredient("ing_garlic", "Garlic", 3f, "cloves"),
                RecipeIngredient("ing_lemon", "Lemon", 1f, "pcs"),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 1f, "tbsp")
            ),
            steps = listOf(
                RecipeStep(1, "Pat salmon fillets dry with paper towels and season with a pinch of salt and pepper."),
                RecipeStep(2, "In a small bowl, whisk together honey, soy sauce, minced garlic, and 1 tbsp lemon juice."),
                RecipeStep(3, "Heat olive oil in a skillet over medium-high heat. Place salmon skin-side up and sear for 4 minutes.", timerSeconds = 240),
                RecipeStep(4, "Flip salmon carefully, pour glaze over the fish, and baste continuously for 3-4 minutes until sauce bubbles and thickens.", timerSeconds = 210),
                RecipeStep(5, "Serve immediately with extra pan glaze drizzled over top.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 380, proteinGrams = 35, carbsGrams = 18, fatGrams = 19),
            tags = listOf("High Protein", "Under 20 Minutes", "Pescatarian", "Date Night"),
            isHighProtein = true,
            rating = 4.9f,
            reviewsCount = 280
        ),

        Recipe(
            id = "rec_08",
            title = "Loaded Breakfast Scramble",
            description = "Fluffy scrambled eggs folded with diced potatoes, caramelized onions, sweet tomatoes, and melted cheese.",
            imageUrl = "https://images.unsplash.com/photo-1525351484163-7529414344d8?auto=format&fit=crop&w=800&q=80",
            cuisine = "American",
            mealTypes = listOf(MealType.BREAKFAST, MealType.LUNCH),
            prepTimeMinutes = 5,
            cookTimeMinutes = 10,
            servings = 2,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_eggs", "Eggs", 4f, "pcs"),
                RecipeIngredient("ing_potato", "Potato", 1f, "pcs"),
                RecipeIngredient("ing_onion", "Onion", 0.5f, "pcs"),
                RecipeIngredient("ing_tomato", "Tomato", 1f, "pcs"),
                RecipeIngredient("ing_cheese_cheddar", "Cheddar Cheese", 40f, "g"),
                RecipeIngredient("ing_butter", "Butter", 1f, "tbsp"),
                RecipeIngredient("ing_salt", "Salt", 0.5f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Dice potato into tiny 1/2 cm cubes so they cook rapidly. Dice onion and tomato."),
                RecipeStep(2, "Melt butter in skillet, add potato cubes and onions; cook for 5 minutes until tender and browned.", timerSeconds = 300),
                RecipeStep(3, "Add diced tomato and cook for 1 minute.", timerSeconds = 60),
                RecipeStep(4, "Pour beaten eggs directly into pan, reduce heat to medium-low, and push eggs gently until soft curds form.", timerSeconds = 120),
                RecipeStep(5, "Remove from heat, sprinkle cheddar cheese, and let it melt for 30 seconds before plating.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 340, proteinGrams = 20, carbsGrams = 22, fatGrams = 20),
            tags = listOf("Breakfast", "Under 15 Minutes", "Vegetarian", "High Protein"),
            isVegetarian = true,
            isHighProtein = true,
            rating = 4.7f,
            reviewsCount = 145
        ),

        Recipe(
            id = "rec_09",
            title = "Crispy Golden Garlic Grilled Cheese",
            description = "Sourdough bread crusted with garlic butter and stuffed with double melted cheddar and mozzarella cheese.",
            imageUrl = "https://images.unsplash.com/photo-1528735602780-2552fd46c7af?auto=format&fit=crop&w=800&q=80",
            cuisine = "American",
            mealTypes = listOf(MealType.LUNCH, MealType.SNACK),
            prepTimeMinutes = 3,
            cookTimeMinutes = 6,
            servings = 1,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_bread", "Bread", 2f, "slices"),
                RecipeIngredient("ing_cheese_cheddar", "Cheddar Cheese", 40f, "g"),
                RecipeIngredient("ing_cheese_mozzarella", "Mozzarella Cheese", 40f, "g"),
                RecipeIngredient("ing_butter", "Butter", 1.5f, "tbsp"),
                RecipeIngredient("ing_garlic", "Garlic", 1f, "cloves")
            ),
            steps = listOf(
                RecipeStep(1, "Mince garlic and mix with softened butter in a small ramekin."),
                RecipeStep(2, "Spread garlic butter evenly on outer side of both bread slices."),
                RecipeStep(3, "Layer cheddar and mozzarella between unbuttered sides of bread."),
                RecipeStep(4, "Place in a cold skillet, turn heat to medium-low, and cook for 3 minutes per side until deeply golden and cheese is molten.", timerSeconds = 360)
            ),
            nutrition = NutritionInfo(calories = 460, proteinGrams = 21, carbsGrams = 32, fatGrams = 28),
            tags = listOf("Quick & Easy", "Under 15 Minutes", "Vegetarian", "Comfort Food"),
            isVegetarian = true,
            rating = 4.8f,
            reviewsCount = 330
        ),

        Recipe(
            id = "rec_10",
            title = "Garlic Chili Shrimp Stir-Fry",
            description = "Succulent shrimp tossed in sizzling garlic, red pepper flakes, olive oil, and finished with a squeeze of fresh lemon.",
            imageUrl = "https://images.unsplash.com/photo-1559742811-822873691df8?auto=format&fit=crop&w=800&q=80",
            cuisine = "Mediterranean",
            mealTypes = listOf(MealType.DINNER, MealType.LUNCH),
            prepTimeMinutes = 5,
            cookTimeMinutes = 7,
            servings = 2,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_shrimp", "Shrimp", 300f, "g"),
                RecipeIngredient("ing_garlic", "Garlic", 5f, "cloves"),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 2f, "tbsp"),
                RecipeIngredient("ing_chili_flakes", "Red Chili Flakes", 1f, "tsp"),
                RecipeIngredient("ing_lemon", "Lemon", 0.5f, "pcs"),
                RecipeIngredient("ing_salt", "Salt", 0.5f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Peel and devein shrimp; pat thoroughly dry with paper towels."),
                RecipeStep(2, "Heat olive oil in a skillet over medium heat and sauté sliced garlic until fragrant (do not burn).", timerSeconds = 60),
                RecipeStep(3, "Add shrimp and red chili flakes. Cook undisturbed for 2 minutes.", timerSeconds = 120),
                RecipeStep(4, "Flip shrimp and cook for another 1-2 minutes until pink and opaque.", timerSeconds = 90),
                RecipeStep(5, "Remove from heat, squeeze lemon juice, and serve immediately.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 260, proteinGrams = 32, carbsGrams = 4, fatGrams = 13),
            tags = listOf("High Protein", "Under 15 Minutes", "Low Carb", "Pescatarian", "Keto"),
            isHighProtein = true,
            isLowCarb = true,
            isKeto = true,
            rating = 4.9f,
            reviewsCount = 270
        ),

        Recipe(
            id = "rec_11",
            title = "Creamy Tomato & Basil Penne",
            description = "Penne pasta enveloped in a rich, velvety pink sauce made with sweet tomatoes, garlic, cream, and fresh basil leaves.",
            imageUrl = "https://images.unsplash.com/photo-1621996346565-e3d5d6281699?auto=format&fit=crop&w=800&q=80",
            cuisine = "Italian",
            mealTypes = listOf(MealType.DINNER, MealType.LUNCH),
            prepTimeMinutes = 5,
            cookTimeMinutes = 15,
            servings = 3,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_penne", "Penne Pasta", 250f, "g"),
                RecipeIngredient("ing_canned_tomatoes", "Diced Canned Tomatoes", 1f, "can"),
                RecipeIngredient("ing_garlic", "Garlic", 4f, "cloves"),
                RecipeIngredient("ing_heavy_cream", "Heavy Cream", 100f, "ml"),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 2f, "tbsp"),
                RecipeIngredient("ing_basil_fresh", "Fresh Basil", 8f, "leaves", optional = true),
                RecipeIngredient("ing_cheese_parmesan", "Parmesan Cheese", 30f, "g", optional = true)
            ),
            steps = listOf(
                RecipeStep(1, "Boil salted water and cook penne for 10 minutes until al dente.", timerSeconds = 600),
                RecipeStep(2, "In a deep pan, sauté minced garlic in olive oil for 1 minute.", timerSeconds = 60),
                RecipeStep(3, "Pour in canned tomatoes, season with salt and simmer for 6 minutes.", timerSeconds = 360),
                RecipeStep(4, "Stir in heavy cream and simmer until sauce turns luscious orange-pink.", timerSeconds = 120),
                RecipeStep(5, "Toss drained penne into sauce with fresh basil and parmesan.", timerSeconds = 60)
            ),
            nutrition = NutritionInfo(calories = 440, proteinGrams = 13, carbsGrams = 62, fatGrams = 16),
            tags = listOf("Vegetarian", "Italian Classics", "Under 30 Minutes", "Comfort Food"),
            isVegetarian = true,
            rating = 4.8f,
            reviewsCount = 380
        ),

        Recipe(
            id = "rec_12",
            title = "Savory Egg Fried Rice",
            description = "Day-old rice stir-fried in a hot wok with scrambled eggs, scallions, soy sauce, and toasted sesame oil.",
            imageUrl = "https://images.unsplash.com/photo-1603133872878-684f208fb84b?auto=format&fit=crop&w=800&q=80",
            cuisine = "Asian",
            mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
            prepTimeMinutes = 5,
            cookTimeMinutes = 8,
            servings = 2,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_rice_basmati", "Basmati Rice", 300f, "g"),
                RecipeIngredient("ing_eggs", "Eggs", 3f, "pcs"),
                RecipeIngredient("ing_onion", "Onion", 1f, "pcs"),
                RecipeIngredient("ing_soy_sauce", "Soy Sauce", 2f, "tbsp"),
                RecipeIngredient("ing_sesame_oil", "Sesame Oil", 1f, "tsp"),
                RecipeIngredient("ing_vegetable_oil", "Vegetable Oil", 2f, "tbsp"),
                RecipeIngredient("ing_garlic", "Garlic", 2f, "cloves")
            ),
            steps = listOf(
                RecipeStep(1, "Beat eggs lightly with a dash of salt."),
                RecipeStep(2, "Heat 1 tbsp oil in wok/skillet over high heat, scramble eggs softly for 45 seconds, and remove.", timerSeconds = 45),
                RecipeStep(3, "Add remaining oil, sauté diced onion and garlic for 1 minute.", timerSeconds = 60),
                RecipeStep(4, "Add cold cooked rice, breaking clumps with spatula, and stir-fry for 3 minutes.", timerSeconds = 180),
                RecipeStep(5, "Drizzle soy sauce and sesame oil around pan edges, fold eggs back in, and toss vigorously.", timerSeconds = 60)
            ),
            nutrition = NutritionInfo(calories = 360, proteinGrams = 14, carbsGrams = 52, fatGrams = 11),
            tags = listOf("Quick & Easy", "Pantry Staples", "Budget Friendly", "Vegetarian"),
            isVegetarian = true,
            rating = 4.8f,
            reviewsCount = 490
        ),

        Recipe(
            id = "rec_13",
            title = "Hearty Shakshuka (Eggs in Tomato Sauce)",
            description = "Poached eggs gently simmered in a spiced bell pepper and tomato sauce with cumin, paprika, and garlic.",
            imageUrl = "https://images.unsplash.com/photo-1590412200988-a436970781fa?auto=format&fit=crop&w=800&q=80",
            cuisine = "Mediterranean",
            mealTypes = listOf(MealType.BREAKFAST, MealType.BRUNCH, MealType.DINNER),
            prepTimeMinutes = 8,
            cookTimeMinutes = 18,
            servings = 3,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_eggs", "Eggs", 4f, "pcs"),
                RecipeIngredient("ing_canned_tomatoes", "Diced Canned Tomatoes", 1f, "can"),
                RecipeIngredient("ing_bell_pepper", "Bell Pepper", 1f, "pcs"),
                RecipeIngredient("ing_onion", "Onion", 1f, "pcs"),
                RecipeIngredient("ing_garlic", "Garlic", 3f, "cloves"),
                RecipeIngredient("ing_cumin", "Cumin Seeds / Powder", 1f, "tsp"),
                RecipeIngredient("ing_paprika", "Smoked Paprika", 1f, "tsp"),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 2f, "tbsp")
            ),
            steps = listOf(
                RecipeStep(1, "Dice onion and bell pepper. Heat olive oil in a skillet and sauté for 5 minutes until soft.", timerSeconds = 300),
                RecipeStep(2, "Add garlic, cumin, and smoked paprika; stir for 1 minute.", timerSeconds = 60),
                RecipeStep(3, "Pour in canned tomatoes, season with salt, and simmer sauce for 8 minutes until thickened.", timerSeconds = 480),
                RecipeStep(4, "Use a spoon to make 4 small wells in the sauce. Crack an egg into each well.", tip = "Crack each egg into a cup first to avoid broken yolks."),
                RecipeStep(5, "Cover pan and cook on medium-low for 5 minutes until egg whites are set and yolks remain runny.", timerSeconds = 300)
            ),
            nutrition = NutritionInfo(calories = 240, proteinGrams = 15, carbsGrams = 16, fatGrams = 14),
            tags = listOf("Vegetarian", "High Protein", "Mediterranean", "Weekend Cooking"),
            isVegetarian = true,
            isHighProtein = true,
            rating = 4.9f,
            reviewsCount = 370
        ),

        Recipe(
            id = "rec_14",
            title = "Crispy Parmesan Garlic Roasted Potatoes",
            description = "Golden potato wedges roasted until ultra-crisp on the outside, fluffy inside, crusted with parmesan and herbs.",
            imageUrl = "https://images.unsplash.com/photo-1598515214211-89d3c73ae83b?auto=format&fit=crop&w=800&q=80",
            cuisine = "American",
            mealTypes = listOf(MealType.DINNER, MealType.SNACK),
            prepTimeMinutes = 8,
            cookTimeMinutes = 25,
            servings = 3,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_potato", "Potato", 4f, "pcs"),
                RecipeIngredient("ing_cheese_parmesan", "Parmesan Cheese", 50f, "g"),
                RecipeIngredient("ing_garlic", "Garlic", 4f, "cloves"),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 2.5f, "tbsp"),
                RecipeIngredient("ing_paprika", "Smoked Paprika", 1f, "tsp"),
                RecipeIngredient("ing_oregano", "Dried Oregano", 1f, "tsp"),
                RecipeIngredient("ing_salt", "Salt", 1f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Cut potatoes into wedges or 1-inch chunks."),
                RecipeStep(2, "Toss potatoes with olive oil, minced garlic, smoked paprika, oregano, and salt."),
                RecipeStep(3, "Spread in a single layer on a baking sheet, cut-side down, coated in grated parmesan."),
                RecipeStep(4, "Roast or air fry at 200°C (400°F) for 22-25 minutes until deeply golden and crispy.", timerSeconds = 1320),
                RecipeStep(5, "Let rest for 2 minutes before lifting with a spatula to keep the parmesan crust intact.", timerSeconds = 120)
            ),
            nutrition = NutritionInfo(calories = 290, proteinGrams = 8, carbsGrams = 39, fatGrams = 12),
            tags = listOf("Budget Friendly", "Vegetarian", "Gluten Free", "Side Dish"),
            isVegetarian = true,
            isGlutenFree = true,
            rating = 4.8f,
            reviewsCount = 260
        ),

        Recipe(
            id = "rec_15",
            title = "Chicken Tinga Tacos",
            description = "Smoky shredded chicken simmered with tomatoes, onions, garlic, and Mexican oregano served in warm tortillas.",
            imageUrl = "https://images.unsplash.com/photo-1565299585323-38d6b0865b47?auto=format&fit=crop&w=800&q=80",
            cuisine = "Mexican",
            mealTypes = listOf(MealType.DINNER, MealType.LUNCH),
            prepTimeMinutes = 10,
            cookTimeMinutes = 18,
            servings = 3,
            difficulty = DifficultyLevel.MEDIUM,
            ingredients = listOf(
                RecipeIngredient("ing_chicken_breast", "Chicken Breast", 400f, "g"),
                RecipeIngredient("ing_tomato", "Tomato", 3f, "pcs"),
                RecipeIngredient("ing_onion", "Onion", 1.5f, "pcs"),
                RecipeIngredient("ing_garlic", "Garlic", 3f, "cloves"),
                RecipeIngredient("ing_tortilla", "Tortilla", 6f, "pcs"),
                RecipeIngredient("ing_cumin", "Cumin Seeds / Powder", 1f, "tsp"),
                RecipeIngredient("ing_oregano", "Dried Oregano", 1f, "tsp"),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 1f, "tbsp")
            ),
            steps = listOf(
                RecipeStep(1, "Poach chicken breast in simmering salted water for 12 minutes, then shred with two forks.", timerSeconds = 720),
                RecipeStep(2, "Blend tomatoes, garlic, cumin, and oregano into a smooth sauce."),
                RecipeStep(3, "Slice remaining onions into thin rings and cook in olive oil until golden.", timerSeconds = 240),
                RecipeStep(4, "Add tomato sauce and shredded chicken to onions, simmering for 6 minutes until chicken absorbs sauce.", timerSeconds = 360),
                RecipeStep(5, "Warm tortillas on a dry skillet and fill with smoky chicken tinga.", timerSeconds = 120)
            ),
            nutrition = NutritionInfo(calories = 380, proteinGrams = 36, carbsGrams = 34, fatGrams = 10),
            tags = listOf("High Protein", "Mexican", "Under 30 Minutes", "Family Favorite"),
            isHighProtein = true,
            rating = 4.9f,
            reviewsCount = 310
        ),

        Recipe(
            id = "rec_16",
            title = "Quick Garlic Butter Mushroom Saute",
            description = "Earthy sliced mushrooms caramelized in brown butter with garlic, black pepper, and fresh lemon.",
            imageUrl = "https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&w=800&q=80",
            cuisine = "French",
            mealTypes = listOf(MealType.DINNER, MealType.LUNCH, MealType.SNACK),
            prepTimeMinutes = 5,
            cookTimeMinutes = 8,
            servings = 2,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_mushroom", "Mushroom", 300f, "g"),
                RecipeIngredient("ing_butter", "Butter", 2f, "tbsp"),
                RecipeIngredient("ing_garlic", "Garlic", 4f, "cloves"),
                RecipeIngredient("ing_black_pepper", "Black Pepper", 1f, "tsp"),
                RecipeIngredient("ing_lemon", "Lemon", 0.5f, "pcs", optional = true)
            ),
            steps = listOf(
                RecipeStep(1, "Wipe mushrooms clean with damp cloth and cut into thick slices."),
                RecipeStep(2, "Melt butter in a wide skillet over high heat until foaming.", timerSeconds = 60),
                RecipeStep(3, "Add mushrooms in a single layer without overcrowding. Cook undisturbed for 3 minutes to caramelize.", timerSeconds = 180),
                RecipeStep(4, "Toss mushrooms, add minced garlic and fresh black pepper, cooking for 2 more minutes.", timerSeconds = 120),
                RecipeStep(5, "Finish with a squeeze of fresh lemon juice and serve hot.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 160, proteinGrams = 5, carbsGrams = 6, fatGrams = 13),
            tags = listOf("Quick & Easy", "Low Carb", "Keto", "Vegetarian", "Under 15 Minutes"),
            isVegetarian = true,
            isLowCarb = true,
            isKeto = true,
            rating = 4.7f,
            reviewsCount = 195
        ),

        Recipe(
            id = "rec_17",
            title = "Creamy Spinach & Garlic Dip",
            description = "Warm bubbling dip loaded with wilted spinach, melted cheddar, mozzarella, and garlic. Perfect with crusty bread.",
            imageUrl = "https://images.unsplash.com/photo-1541544741938-0af808871cc0?auto=format&fit=crop&w=800&q=80",
            cuisine = "American",
            mealTypes = listOf(MealType.SNACK, MealType.BRUNCH),
            prepTimeMinutes = 5,
            cookTimeMinutes = 10,
            servings = 3,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_spinach", "Spinach", 200f, "g"),
                RecipeIngredient("ing_garlic", "Garlic", 3f, "cloves"),
                RecipeIngredient("ing_heavy_cream", "Heavy Cream", 80f, "ml"),
                RecipeIngredient("ing_cheese_mozzarella", "Mozzarella Cheese", 60f, "g"),
                RecipeIngredient("ing_cheese_cheddar", "Cheddar Cheese", 40f, "g"),
                RecipeIngredient("ing_bread", "Bread", 4f, "slices", optional = true)
            ),
            steps = listOf(
                RecipeStep(1, "Sauté spinach and minced garlic in a pan for 2 minutes until wilted.", timerSeconds = 120),
                RecipeStep(2, "Reduce heat and stir in heavy cream until gently bubbling.", timerSeconds = 60),
                RecipeStep(3, "Fold in mozzarella and cheddar until completely melted and stringy.", timerSeconds = 120),
                RecipeStep(4, "Serve warm with toasted bread slices for dipping.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 310, proteinGrams = 15, carbsGrams = 8, fatGrams = 24),
            tags = listOf("Keto", "Vegetarian", "Snacks", "Under 15 Minutes"),
            isVegetarian = true,
            isKeto = true,
            rating = 4.8f,
            reviewsCount = 160
        ),

        Recipe(
            id = "rec_18",
            title = "Quick Masala Egg Toast",
            description = "Crispy spiced egg-soaked French toast street-style with onions, green chilies, tomatoes, and cilantro.",
            imageUrl = "https://images.unsplash.com/photo-1482049016688-2d3e1b311543?auto=format&fit=crop&w=800&q=80",
            cuisine = "Indian",
            mealTypes = listOf(MealType.BREAKFAST, MealType.SNACK),
            prepTimeMinutes = 5,
            cookTimeMinutes = 6,
            servings = 1,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_eggs", "Eggs", 2f, "pcs"),
                RecipeIngredient("ing_bread", "Bread", 2f, "slices"),
                RecipeIngredient("ing_onion", "Onion", 0.5f, "pcs"),
                RecipeIngredient("ing_tomato", "Tomato", 0.5f, "pcs"),
                RecipeIngredient("ing_green_chili", "Green Chili", 1f, "pcs", optional = true),
                RecipeIngredient("ing_butter", "Butter", 1f, "tbsp"),
                RecipeIngredient("ing_turmeric", "Turmeric Powder", 0.25f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Finely chop onion, tomato, and green chili."),
                RecipeStep(2, "Beat eggs in a wide bowl with chopped veggies, turmeric, and salt."),
                RecipeStep(3, "Melt butter in a skillet over medium heat.", timerSeconds = 45),
                RecipeStep(4, "Dip bread slices into the egg mixture and cook for 2-3 minutes per side until golden brown.", timerSeconds = 300)
            ),
            nutrition = NutritionInfo(calories = 320, proteinGrams = 16, carbsGrams = 28, fatGrams = 15),
            tags = listOf("Breakfast", "Indian Favorites", "Under 15 Minutes", "Quick & Easy"),
            isVegetarian = true,
            rating = 4.8f,
            reviewsCount = 210
        ),

        Recipe(
            id = "rec_19",
            title = "Creamy Broccoli Potato Soup",
            description = "Velvety smooth comfort soup made from tender broccoli florets, creamy potatoes, onions, and sharp cheddar cheese.",
            imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?auto=format&fit=crop&w=800&q=80",
            cuisine = "American",
            mealTypes = listOf(MealType.DINNER, MealType.LUNCH),
            prepTimeMinutes = 10,
            cookTimeMinutes = 20,
            servings = 4,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_broccoli", "Broccoli", 300f, "g"),
                RecipeIngredient("ing_potato", "Potato", 2f, "pcs"),
                RecipeIngredient("ing_onion", "Onion", 1f, "pcs"),
                RecipeIngredient("ing_garlic", "Garlic", 3f, "cloves"),
                RecipeIngredient("ing_milk", "Milk", 200f, "ml"),
                RecipeIngredient("ing_cheese_cheddar", "Cheddar Cheese", 60f, "g"),
                RecipeIngredient("ing_butter", "Butter", 1.5f, "tbsp")
            ),
            steps = listOf(
                RecipeStep(1, "Chop broccoli, peel and dice potatoes, and mince onion and garlic."),
                RecipeStep(2, "Melt butter in a pot and sauté onion and garlic for 3 minutes.", timerSeconds = 180),
                RecipeStep(3, "Add potatoes, broccoli florets, and 2 cups of water or broth. Simmer for 12 minutes until fork-tender.", timerSeconds = 720),
                RecipeStep(4, "Blend soup with an immersion blender until velvety smooth."),
                RecipeStep(5, "Stir in milk and cheddar cheese over low heat until cheese melts.", timerSeconds = 120)
            ),
            nutrition = NutritionInfo(calories = 270, proteinGrams = 12, carbsGrams = 29, fatGrams = 12),
            tags = listOf("Vegetarian", "Comfort Food", "Under 30 Minutes", "Gluten Free"),
            isVegetarian = true,
            isGlutenFree = true,
            rating = 4.8f,
            reviewsCount = 220
        ),

        Recipe(
            id = "rec_20",
            title = "One-Pan Lemon Herb Chicken Thighs",
            description = "Crisp-skinned chicken thighs roasted with sweet red onions, garlic cloves, and fresh lemon wedges.",
            imageUrl = "https://images.unsplash.com/photo-1598515213692-5f252f75d785?auto=format&fit=crop&w=800&q=80",
            cuisine = "Mediterranean",
            mealTypes = listOf(MealType.DINNER),
            prepTimeMinutes = 8,
            cookTimeMinutes = 25,
            servings = 3,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_chicken_thighs", "Chicken Thighs", 500f, "g"),
                RecipeIngredient("ing_lemon", "Lemon", 1f, "pcs"),
                RecipeIngredient("ing_onion", "Onion", 1f, "pcs"),
                RecipeIngredient("ing_garlic", "Garlic", 6f, "cloves"),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 2f, "tbsp"),
                RecipeIngredient("ing_oregano", "Dried Oregano", 1f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Season chicken thighs with salt, black pepper, and dried oregano."),
                RecipeStep(2, "Heat olive oil in an oven-safe skillet over high heat. Sear chicken skin-side down for 5 minutes until crispy.", timerSeconds = 300),
                RecipeStep(3, "Flip chicken, scatter whole peeled garlic cloves, onion quarters, and lemon slices around.", timerSeconds = 60),
                RecipeStep(4, "Transfer skillet to 200°C (400°F) oven or cover and cook over medium-low for 18 minutes.", timerSeconds = 1080),
                RecipeStep(5, "Spoon pan juices over thighs and serve.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 430, proteinGrams = 42, carbsGrams = 5, fatGrams = 27),
            tags = listOf("High Protein", "Keto", "Low Carb", "One Pot", "Gluten Free"),
            isHighProtein = true,
            isKeto = true,
            isLowCarb = true,
            isGlutenFree = true,
            rating = 4.9f,
            reviewsCount = 390
        ),

        Recipe(
            id = "rec_21",
            title = "Homestyle Tomato Egg Drop Soup",
            description = "Comforting Chinese home-style soup with simmered fresh tomatoes and silky egg ribbons drizzled with sesame oil.",
            imageUrl = "https://images.unsplash.com/photo-1541832676-9b763b0239ab?auto=format&fit=crop&w=800&q=80",
            cuisine = "Chinese",
            mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
            prepTimeMinutes = 5,
            cookTimeMinutes = 10,
            servings = 2,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_tomato", "Tomato", 3f, "pcs"),
                RecipeIngredient("ing_eggs", "Eggs", 2f, "pcs"),
                RecipeIngredient("ing_garlic", "Garlic", 2f, "cloves"),
                RecipeIngredient("ing_sesame_oil", "Sesame Oil", 1f, "tsp"),
                RecipeIngredient("ing_soy_sauce", "Soy Sauce", 1f, "tbsp"),
                RecipeIngredient("ing_cornstarch", "Cornstarch", 1f, "tbsp", optional = true)
            ),
            steps = listOf(
                RecipeStep(1, "Chop ripe tomatoes into wedges. Beat eggs with a pinch of salt."),
                RecipeStep(2, "Heat 1 tbsp oil in a saucepan, add garlic and tomatoes. Cook for 3 minutes until tomatoes break down into juicy pulp.", timerSeconds = 180),
                RecipeStep(3, "Add 3 cups of water and soy sauce. Bring to a rapid rolling boil.", timerSeconds = 240),
                RecipeStep(4, "Optional: stir in cornstarch slurry (1 tbsp cornstarch + 2 tbsp water) to lightly thicken.", timerSeconds = 30),
                RecipeStep(5, "Slowly pour beaten eggs in a circular ribbon while gently stirring broth to create silky swirls.", timerSeconds = 30),
                RecipeStep(6, "Remove from heat, drizzle sesame oil, and serve hot.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 145, proteinGrams = 9, carbsGrams = 10, fatGrams = 8),
            tags = listOf("Quick & Easy", "Under 15 Minutes", "Vegetarian", "Budget Friendly", "Low Calorie"),
            isVegetarian = true,
            rating = 4.8f,
            reviewsCount = 185
        ),

        Recipe(
            id = "rec_22",
            title = "Savory Oatmeal with Fried Egg & Cheddar",
            description = "Creamy savory rolled oats cooked with chicken broth or milk, topped with a runny fried egg and melted cheddar.",
            imageUrl = "https://images.unsplash.com/photo-1584776296944-ab6fb57b0bdd?auto=format&fit=crop&w=800&q=80",
            cuisine = "American",
            mealTypes = listOf(MealType.BREAKFAST),
            prepTimeMinutes = 2,
            cookTimeMinutes = 8,
            servings = 1,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_oats", "Rolled Oats", 50f, "g"),
                RecipeIngredient("ing_eggs", "Eggs", 1f, "pcs"),
                RecipeIngredient("ing_cheese_cheddar", "Cheddar Cheese", 25f, "g"),
                RecipeIngredient("ing_butter", "Butter", 1f, "tsp"),
                RecipeIngredient("ing_black_pepper", "Black Pepper", 0.5f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Simmer rolled oats in 1 cup of water or milk with salt for 5 minutes until creamy.", timerSeconds = 300),
                RecipeStep(2, "Melt butter in a small pan and fry egg sunny-side up until white is set and yolk is still molten.", timerSeconds = 180),
                RecipeStep(3, "Stir cheddar cheese into the warm oatmeal.", timerSeconds = 30),
                RecipeStep(4, "Top with the fried egg, cracked black pepper, and serve immediately.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 360, proteinGrams = 18, carbsGrams = 34, fatGrams = 17),
            tags = listOf("Breakfast", "Under 15 Minutes", "Vegetarian", "High Protein"),
            isVegetarian = true,
            isHighProtein = true,
            rating = 4.7f,
            reviewsCount = 130
        ),

        Recipe(
            id = "rec_23",
            title = "Garlic Parmesan Crusted Chicken Breast",
            description = "Juicy chicken breasts breaded with grated parmesan and garlic, pan-crisped to a golden perfection.",
            imageUrl = "https://images.unsplash.com/photo-1532550907401-a500c9a57435?auto=format&fit=crop&w=800&q=80",
            cuisine = "Italian",
            mealTypes = listOf(MealType.DINNER, MealType.LUNCH),
            prepTimeMinutes = 5,
            cookTimeMinutes = 12,
            servings = 2,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_chicken_breast", "Chicken Breast", 400f, "g"),
                RecipeIngredient("ing_cheese_parmesan", "Parmesan Cheese", 50f, "g"),
                RecipeIngredient("ing_garlic", "Garlic", 3f, "cloves"),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 2f, "tbsp"),
                RecipeIngredient("ing_paprika", "Smoked Paprika", 1f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Slice chicken breasts horizontally into thinner cutlets."),
                RecipeStep(2, "Mix finely grated parmesan, minced garlic, smoked paprika, and salt on a flat plate."),
                RecipeStep(3, "Press chicken cutlets firmly into the parmesan mixture on both sides."),
                RecipeStep(4, "Heat olive oil in a skillet over medium heat. Sear chicken for 4-5 minutes per side until golden crust forms.", timerSeconds = 480),
                RecipeStep(5, "Rest chicken for 3 minutes before slicing to keep juices locked in.", timerSeconds = 180)
            ),
            nutrition = NutritionInfo(calories = 410, proteinGrams = 48, carbsGrams = 3, fatGrams = 22),
            tags = listOf("High Protein", "Keto", "Low Carb", "Quick & Easy", "Under 20 Minutes"),
            isHighProtein = true,
            isKeto = true,
            isLowCarb = true,
            rating = 4.9f,
            reviewsCount = 420
        ),

        Recipe(
            id = "rec_24",
            title = "Creamy Garlic Butter Zucchini Noodles",
            description = "Fresh spiralized zucchini noodles sautéed with garlic, butter, sweet cream, and freshly cracked pepper.",
            imageUrl = "https://images.unsplash.com/photo-1540420773420-3366772f4999?auto=format&fit=crop&w=800&q=80",
            cuisine = "Italian",
            mealTypes = listOf(MealType.DINNER, MealType.LUNCH),
            prepTimeMinutes = 6,
            cookTimeMinutes = 6,
            servings = 2,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_zucchini", "Zucchini", 3f, "pcs"),
                RecipeIngredient("ing_butter", "Butter", 2f, "tbsp"),
                RecipeIngredient("ing_garlic", "Garlic", 4f, "cloves"),
                RecipeIngredient("ing_cheese_parmesan", "Parmesan Cheese", 30f, "g"),
                RecipeIngredient("ing_heavy_cream", "Heavy Cream", 60f, "ml")
            ),
            steps = listOf(
                RecipeStep(1, "Spiralize zucchini or slice thinly into noodle strands with a peeler."),
                RecipeStep(2, "Melt butter in a pan over medium heat; sauté minced garlic for 1 minute.", timerSeconds = 60),
                RecipeStep(3, "Add heavy cream and parmesan; simmer for 1 minute until creamy.", timerSeconds = 60),
                RecipeStep(4, "Toss zucchini noodles in the sauce for just 2 minutes until tender-crisp.", tip = "Do not overcook or zucchini will release water.", timerSeconds = 120),
                RecipeStep(5, "Serve immediately with extra parmesan on top.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 220, proteinGrams = 6, carbsGrams = 7, fatGrams = 19),
            tags = listOf("Keto", "Low Carb", "Vegetarian", "Under 15 Minutes"),
            isVegetarian = true,
            isKeto = true,
            isLowCarb = true,
            rating = 4.7f,
            reviewsCount = 175
        ),

        Recipe(
            id = "rec_25",
            title = "One-Pot Tomato Chicken Pasta",
            description = "Pasta cooked directly in a savory broth with diced chicken, garlic, tomatoes, and herbs for maximum flavor.",
            imageUrl = "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?auto=format&fit=crop&w=800&q=80",
            cuisine = "Italian",
            mealTypes = listOf(MealType.DINNER),
            prepTimeMinutes = 8,
            cookTimeMinutes = 18,
            servings = 4,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_chicken_breast", "Chicken Breast", 400f, "g"),
                RecipeIngredient("ing_penne", "Penne Pasta", 250f, "g"),
                RecipeIngredient("ing_canned_tomatoes", "Diced Canned Tomatoes", 1f, "can"),
                RecipeIngredient("ing_onion", "Onion", 1f, "pcs"),
                RecipeIngredient("ing_garlic", "Garlic", 3f, "cloves"),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 2f, "tbsp"),
                RecipeIngredient("ing_oregano", "Dried Oregano", 1f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Heat olive oil in a wide pot and brown chicken breast cubes for 4 minutes.", timerSeconds = 240),
                RecipeStep(2, "Add chopped onion, garlic, and oregano; sauté for 2 minutes.", timerSeconds = 120),
                RecipeStep(3, "Pour in canned tomatoes, 2.5 cups water, and dry penne pasta.", timerSeconds = 60),
                RecipeStep(4, "Bring to a boil, cover, and simmer for 11 minutes until pasta is tender and sauce coats every bite.", timerSeconds = 660),
                RecipeStep(5, "Stir well, season with salt, and serve hot.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 430, proteinGrams = 36, carbsGrams = 54, fatGrams = 9),
            tags = listOf("One Pot", "High Protein", "Family Favorite", "Under 30 Minutes"),
            isHighProtein = true,
            rating = 4.9f,
            reviewsCount = 350
        ),

        Recipe(
            id = "rec_26",
            title = "Classic Avocado Toast with Poached Egg",
            description = "Toasted sourdough bread topped with creamy lemon-mashed avocado, chili flakes, and a warm poached egg.",
            imageUrl = "https://images.unsplash.com/photo-1525351484163-7529414344d8?auto=format&fit=crop&w=800&q=80",
            cuisine = "International",
            mealTypes = listOf(MealType.BREAKFAST, MealType.BRUNCH),
            prepTimeMinutes = 5,
            cookTimeMinutes = 5,
            servings = 1,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_bread", "Bread", 2f, "slices"),
                RecipeIngredient("ing_avocado", "Avocado", 1f, "pcs"),
                RecipeIngredient("ing_eggs", "Eggs", 1f, "pcs"),
                RecipeIngredient("ing_lemon", "Lemon", 0.5f, "pcs"),
                RecipeIngredient("ing_chili_flakes", "Red Chili Flakes", 0.5f, "tsp"),
                RecipeIngredient("ing_salt", "Salt", 0.5f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Toast bread slices until golden brown and firm."),
                RecipeStep(2, "Scoop avocado flesh into a bowl, add lemon juice, salt, and coarsely mash with a fork."),
                RecipeStep(3, "Poach or soft boil egg for 4-5 minutes in gently simmering water.", timerSeconds = 270),
                RecipeStep(4, "Spread mashed avocado onto toast, top with egg, and sprinkle red chili flakes.", timerSeconds = 60)
            ),
            nutrition = NutritionInfo(calories = 360, proteinGrams = 14, carbsGrams = 32, fatGrams = 21),
            tags = listOf("Breakfast", "Under 15 Minutes", "Vegetarian", "Healthy"),
            isVegetarian = true,
            rating = 4.8f,
            reviewsCount = 290
        ),

        Recipe(
            id = "rec_27",
            title = "Quick Tuna Salad Lettuce Wraps",
            description = "Flaked tuna tossed with light mayonnaise, diced red onion, lemon juice, black pepper in crisp leaves.",
            imageUrl = "https://images.unsplash.com/photo-1540420773420-3366772f4999?auto=format&fit=crop&w=800&q=80",
            cuisine = "American",
            mealTypes = listOf(MealType.LUNCH, MealType.SNACK),
            prepTimeMinutes = 8,
            cookTimeMinutes = 0,
            servings = 2,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_tuna_canned", "Canned Tuna", 2f, "can"),
                RecipeIngredient("ing_onion", "Onion", 0.5f, "pcs"),
                RecipeIngredient("ing_mayonnaise", "Mayonnaise", 2f, "tbsp"),
                RecipeIngredient("ing_lemon", "Lemon", 0.5f, "pcs"),
                RecipeIngredient("ing_black_pepper", "Black Pepper", 0.5f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Drain canned tuna thoroughly and place in a mixing bowl."),
                RecipeStep(2, "Finely dice onion and add to the bowl."),
                RecipeStep(3, "Mix with mayonnaise, lemon juice, black pepper, and salt until creamy and evenly combined."),
                RecipeStep(4, "Serve wrapped in crisp leaves or spooned over toast.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 230, proteinGrams = 28, carbsGrams = 4, fatGrams = 11),
            tags = listOf("High Protein", "Keto", "Low Carb", "Under 15 Minutes", "Pescatarian"),
            isHighProtein = true,
            isKeto = true,
            isLowCarb = true,
            rating = 4.7f,
            reviewsCount = 140
        ),

        Recipe(
            id = "rec_28",
            title = "Crispy Garlic Butter Croutons",
            description = "Transform leftover bread into ultra-crunchy, savory golden croutons baked with garlic butter and herbs.",
            imageUrl = "https://images.unsplash.com/photo-1509722747041-616f39b57569?auto=format&fit=crop&w=800&q=80",
            cuisine = "International",
            mealTypes = listOf(MealType.SNACK),
            prepTimeMinutes = 4,
            cookTimeMinutes = 10,
            servings = 3,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_bread", "Bread", 4f, "slices"),
                RecipeIngredient("ing_butter", "Butter", 2f, "tbsp"),
                RecipeIngredient("ing_garlic", "Garlic", 2f, "cloves"),
                RecipeIngredient("ing_oregano", "Dried Oregano", 1f, "tsp"),
                RecipeIngredient("ing_salt", "Salt", 0.5f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Cut bread slices into 1/2-inch cubes."),
                RecipeStep(2, "Melt butter in a pan with minced garlic and oregano until aromatic.", timerSeconds = 90),
                RecipeStep(3, "Toss bread cubes in the skillet over medium heat for 7-8 minutes until crisp and golden on all sides.", timerSeconds = 450),
                RecipeStep(4, "Cool for 5 minutes before serving over salads or soups for maximum crunch.", timerSeconds = 300)
            ),
            nutrition = NutritionInfo(calories = 190, proteinGrams = 4, carbsGrams = 24, fatGrams = 9),
            tags = listOf("Pantry Staples", "Vegetarian", "Zero Waste", "Quick & Easy"),
            isVegetarian = true,
            rating = 4.8f,
            reviewsCount = 110
        ),

        Recipe(
            id = "rec_29",
            title = "Quick Garlic Herb Chicken Skewers",
            description = "Juicy cubes of marinated chicken breast infused with garlic, lemon, and olive oil, seared to smoky perfection.",
            imageUrl = "https://images.unsplash.com/photo-1555939594-58d7cb561ad1?auto=format&fit=crop&w=800&q=80",
            cuisine = "Mediterranean",
            mealTypes = listOf(MealType.DINNER, MealType.LUNCH),
            prepTimeMinutes = 10,
            cookTimeMinutes = 10,
            servings = 3,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_chicken_breast", "Chicken Breast", 450f, "g"),
                RecipeIngredient("ing_garlic", "Garlic", 4f, "cloves"),
                RecipeIngredient("ing_lemon", "Lemon", 1f, "pcs"),
                RecipeIngredient("ing_olive_oil", "Olive Oil", 2f, "tbsp"),
                RecipeIngredient("ing_oregano", "Dried Oregano", 1f, "tsp"),
                RecipeIngredient("ing_paprika", "Smoked Paprika", 0.5f, "tsp")
            ),
            steps = listOf(
                RecipeStep(1, "Cut chicken breasts into uniform 1-inch bite-sized cubes."),
                RecipeStep(2, "In a bowl, toss chicken with minced garlic, lemon juice, olive oil, oregano, paprika, and salt."),
                RecipeStep(3, "Thread chicken onto skewers (or cook loose in skillet)."),
                RecipeStep(4, "Heat skillet or grill on high and sear skewers for 4-5 minutes per side until charred and cooked through.", timerSeconds = 540),
                RecipeStep(5, "Rest for 2 minutes and serve with fresh lemon wedges.", timerSeconds = 120)
            ),
            nutrition = NutritionInfo(calories = 290, proteinGrams = 42, carbsGrams = 2, fatGrams = 12),
            tags = listOf("High Protein", "Keto", "Low Carb", "Mediterranean", "Under 20 Minutes"),
            isHighProtein = true,
            isKeto = true,
            isLowCarb = true,
            rating = 4.9f,
            reviewsCount = 280
        ),

        Recipe(
            id = "rec_30",
            title = "Savory Scallion & Garlic Pancakes",
            description = "Crispy pan-fried flatbread layered with sliced onions, fresh herbs, and a savory dipping sauce.",
            imageUrl = "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?auto=format&fit=crop&w=800&q=80",
            cuisine = "Asian",
            mealTypes = listOf(MealType.SNACK, MealType.BREAKFAST),
            prepTimeMinutes = 8,
            cookTimeMinutes = 8,
            servings = 2,
            difficulty = DifficultyLevel.EASY,
            ingredients = listOf(
                RecipeIngredient("ing_flour", "All-Purpose Flour", 150f, "g"),
                RecipeIngredient("ing_onion", "Onion", 1f, "pcs"),
                RecipeIngredient("ing_garlic", "Garlic", 2f, "cloves"),
                RecipeIngredient("ing_sesame_oil", "Sesame Oil", 1f, "tbsp"),
                RecipeIngredient("ing_soy_sauce", "Soy Sauce", 2f, "tbsp"),
                RecipeIngredient("ing_vegetable_oil", "Vegetable Oil", 2f, "tbsp")
            ),
            steps = listOf(
                RecipeStep(1, "Mix flour with 80ml warm water and a pinch of salt to form a soft dough. Rest 5 minutes."),
                RecipeStep(2, "Roll dough into thin rounds, brush with sesame oil, and scatter finely sliced onions and garlic."),
                RecipeStep(3, "Roll into a coil, flatten again with rolling pin."),
                RecipeStep(4, "Heat vegetable oil in a skillet over medium heat; fry pancake for 3-4 minutes per side until blistered and crispy.", timerSeconds = 420),
                RecipeStep(5, "Cut into wedges and serve with soy sauce.", timerSeconds = 30)
            ),
            nutrition = NutritionInfo(calories = 310, proteinGrams = 7, carbsGrams = 46, fatGrams = 11),
            tags = listOf("Vegetarian", "Vegan", "Budget Friendly", "Asian"),
            isVegetarian = true,
            isVegan = true,
            rating = 4.8f,
            reviewsCount = 220
        ),
Recipe(
        id = "rec_31",
        title = "Chicken Tikka Masala",
        description = "Tender tandoori-style chicken simmered in a rich, aromatic tomato and cream sauce.",
        imageUrl = "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?auto=format&fit=crop&w=800&q=80",
        cuisine = "Indian",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 20,
        cookTimeMinutes = 35,
        servings = 4,
        difficulty = DifficultyLevel.MEDIUM,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_chicken_breast",
                name = "Chicken Breast",
                amount = 600f,
                unit = "g",
                optional = false,
                substitutions = listOf("Chicken Thighs", "Paneer")
            ),
            RecipeIngredient(
                ingredientId = "ing_yogurt",
                name = "Plain Yogurt",
                amount = 150f,
                unit = "g",
                optional = false,
                substitutions = listOf("Coconut Yogurt")
            ),
            RecipeIngredient(
                ingredientId = "ing_tomato_puree",
                name = "Tomato Puree",
                amount = 400f,
                unit = "g",
                optional = false,
                substitutions = listOf("Crushed Tomatoes")
            ),
            RecipeIngredient(
                ingredientId = "ing_heavy_cream",
                name = "Heavy Cream",
                amount = 100f,
                unit = "ml",
                optional = false,
                substitutions = listOf("Coconut Cream")
            ),
            RecipeIngredient(
                ingredientId = "ing_garam_masala",
                name = "Garam Masala",
                amount = 2f,
                unit = "tsp",
                optional = false,
                substitutions = listOf("Curry Powder")
            ),
            RecipeIngredient(
                ingredientId = "ing_garlic",
                name = "Garlic",
                amount = 4f,
                unit = "cloves",
                optional = false,
                substitutions = listOf("Garlic Paste")
            ),
            RecipeIngredient(
                ingredientId = "ing_ginger",
                name = "Ginger",
                amount = 1f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Ginger Paste")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Marinate chicken with yogurt, ginger, garlic, garam masala, salt, and chili powder.",
                timerSeconds = 1800,
                tip = "For deeper flavor, marinate overnight."
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Sear the marinated chicken in a hot pan until browned on all sides.",
                timerSeconds = 480,
                tip = "Do not overcrowd the pan."
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Cook tomato puree with spices until the sauce becomes thick and glossy.",
                timerSeconds = 600,
                tip = "Cook until the raw tomato aroma disappears."
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Add chicken and cream, then simmer gently until the chicken is cooked through.",
                timerSeconds = 600,
                tip = "Keep the heat low after adding cream."
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Garnish with fresh cilantro and serve with basmati rice or naan.",
                timerSeconds = null,
                tip = null
            )
        ),
        nutrition = NutritionInfo(
            calories = 520,
            proteinGrams = 43,
            carbsGrams = 18,
            fatGrams = 29
        ),
        tags = listOf("Indian", "Comfort Food", "High Protein", "Authentic"),
        isVegetarian = false,
        isVegan = false,
        isHighProtein = true,
        isLowCarb = false,
        isGlutenFree = true,
        isDairyFree = false,
        isKeto = false,
        rating = 4.8f,
        reviewsCount = 342
    ),

    Recipe(
        id = "rec_32",
        title = "Spaghetti Aglio e Olio",
        description = "A classic Roman-style pasta combining spaghetti, golden garlic, olive oil, chili, and parsley.",
        imageUrl = "https://images.unsplash.com/photo-1621996346565-e3dbc646d9a9?auto=format&fit=crop&w=800&q=80",
        cuisine = "Italian",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 5,
        cookTimeMinutes = 15,
        servings = 2,
        difficulty = DifficultyLevel.EASY,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_spaghetti",
                name = "Spaghetti",
                amount = 200f,
                unit = "g",
                optional = false,
                substitutions = listOf("Linguine", "Gluten-Free Spaghetti")
            ),
            RecipeIngredient(
                ingredientId = "ing_olive_oil",
                name = "Extra Virgin Olive Oil",
                amount = 60f,
                unit = "ml",
                optional = false,
                substitutions = listOf("Avocado Oil")
            ),
            RecipeIngredient(
                ingredientId = "ing_garlic",
                name = "Garlic",
                amount = 5f,
                unit = "cloves",
                optional = false,
                substitutions = listOf("Garlic Paste")
            ),
            RecipeIngredient(
                ingredientId = "ing_red_chili",
                name = "Red Chili Flakes",
                amount = 1f,
                unit = "tsp",
                optional = false,
                substitutions = listOf("Fresh Chili")
            ),
            RecipeIngredient(
                ingredientId = "ing_parsley",
                name = "Fresh Parsley",
                amount = 2f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Fresh Basil")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Cook spaghetti in well-salted boiling water until al dente. Reserve some pasta water.",
                timerSeconds = 540,
                tip = "Keep the pasta slightly firm because it will finish in the sauce."
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Warm olive oil over low heat and gently fry sliced garlic until lightly golden.",
                timerSeconds = 180,
                tip = "Avoid browning the garlic too deeply."
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Add chili flakes and briefly toast them in the garlic oil.",
                timerSeconds = 30,
                tip = null
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Toss in spaghetti and enough pasta water to create a glossy emulsion.",
                timerSeconds = 120,
                tip = "Toss vigorously for the best texture."
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Finish with parsley and serve immediately.",
                timerSeconds = null,
                tip = null
            )
        ),
        nutrition = NutritionInfo(
            calories = 610,
            proteinGrams = 16,
            carbsGrams = 78,
            fatGrams = 27
        ),
        tags = listOf("Italian", "Quick", "Vegetarian", "Classic"),
        isVegetarian = true,
        isVegan = true,
        isHighProtein = false,
        isLowCarb = false,
        isGlutenFree = false,
        isDairyFree = true,
        isKeto = false,
        rating = 4.7f,
        reviewsCount = 287
    ),

    Recipe(
        id = "rec_33",
        title = "Chicken Tacos",
        description = "Juicy spiced chicken tucked into warm corn tortillas with fresh salsa, lime, and cilantro.",
        imageUrl = "https://images.unsplash.com/photo-1552332386-f8dd00dc2f85?auto=format&fit=crop&w=800&q=80",
        cuisine = "Mexican",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 15,
        cookTimeMinutes = 20,
        servings = 4,
        difficulty = DifficultyLevel.EASY,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_chicken_thighs",
                name = "Chicken Thighs",
                amount = 500f,
                unit = "g",
                optional = false,
                substitutions = listOf("Chicken Breast", "Tofu")
            ),
            RecipeIngredient(
                ingredientId = "ing_corn_tortillas",
                name = "Corn Tortillas",
                amount = 8f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Flour Tortillas")
            ),
            RecipeIngredient(
                ingredientId = "ing_tomato",
                name = "Tomato",
                amount = 2f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Cherry Tomatoes")
            ),
            RecipeIngredient(
                ingredientId = "ing_red_onion",
                name = "Red Onion",
                amount = 0.5f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("White Onion")
            ),
            RecipeIngredient(
                ingredientId = "ing_lime",
                name = "Lime",
                amount = 2f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Lemon")
            ),
            RecipeIngredient(
                ingredientId = "ing_cumin",
                name = "Ground Cumin",
                amount = 1f,
                unit = "tsp",
                optional = false,
                substitutions = listOf("Taco Seasoning")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Season chicken with cumin, chili powder, salt, pepper, and lime juice.",
                timerSeconds = 600,
                tip = "Let the chicken rest briefly so the seasoning absorbs."
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Cook chicken in a hot skillet until browned and fully cooked.",
                timerSeconds = 720,
                tip = "Allow the chicken to develop a deep sear."
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Dice tomato and onion and mix with lime juice, salt, and cilantro to make a fresh salsa.",
                timerSeconds = null,
                tip = null
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Warm tortillas on a dry skillet until soft and lightly charred.",
                timerSeconds = 90,
                tip = null
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Slice chicken and assemble tacos with salsa and fresh lime.",
                timerSeconds = null,
                tip = null
            )
        ),
        nutrition = NutritionInfo(
            calories = 430,
            proteinGrams = 35,
            carbsGrams = 38,
            fatGrams = 17
        ),
        tags = listOf("Mexican", "Quick Dinners", "Street Food", "High Protein"),
        isVegetarian = false,
        isVegan = false,
        isHighProtein = true,
        isLowCarb = false,
        isGlutenFree = true,
        isDairyFree = true,
        isKeto = false,
        rating = 4.9f,
        reviewsCount = 421
    ),

    Recipe(
        id = "rec_34",
        title = "Chicken Teriyaki Bowl",
        description = "Japanese-inspired glazed chicken served over steamed rice with crisp vegetables and sesame.",
        imageUrl = "https://images.unsplash.com/photo-1547592180-85f173990554?auto=format&fit=crop&w=800&q=80",
        cuisine = "Japanese",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 15,
        cookTimeMinutes = 20,
        servings = 3,
        difficulty = DifficultyLevel.EASY,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_chicken_thighs",
                name = "Chicken Thighs",
                amount = 450f,
                unit = "g",
                optional = false,
                substitutions = listOf("Chicken Breast", "Tofu")
            ),
            RecipeIngredient(
                ingredientId = "ing_soy_sauce",
                name = "Soy Sauce",
                amount = 4f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Tamari", "Coconut Aminos")
            ),
            RecipeIngredient(
                ingredientId = "ing_honey",
                name = "Honey",
                amount = 2f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Brown Sugar")
            ),
            RecipeIngredient(
                ingredientId = "ing_rice",
                name = "Cooked White Rice",
                amount = 450f,
                unit = "g",
                optional = false,
                substitutions = listOf("Brown Rice")
            ),
            RecipeIngredient(
                ingredientId = "ing_broccoli",
                name = "Broccoli",
                amount = 200f,
                unit = "g",
                optional = false,
                substitutions = listOf("Green Beans")
            ),
            RecipeIngredient(
                ingredientId = "ing_sesame_seeds",
                name = "Sesame Seeds",
                amount = 1f,
                unit = "tbsp",
                optional = true,
                substitutions = listOf("Sliced Green Onion")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Mix soy sauce, honey, ginger, garlic, and a splash of water to make the teriyaki sauce.",
                timerSeconds = null,
                tip = null
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Sear chicken pieces in a hot skillet until browned and nearly cooked through.",
                timerSeconds = 480,
                tip = null
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Pour in the teriyaki sauce and simmer until thick and glossy.",
                timerSeconds = 240,
                tip = "Keep stirring so the honey does not burn."
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Steam or sauté broccoli until bright green and tender-crisp.",
                timerSeconds = 300,
                tip = null
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Serve chicken and broccoli over rice and garnish with sesame seeds.",
                timerSeconds = null,
                tip = null
            )
        ),
        nutrition = NutritionInfo(
            calories = 570,
            proteinGrams = 39,
            carbsGrams = 67,
            fatGrams = 16
        ),
        tags = listOf("Japanese", "Meal Prep", "High Protein", "Rice Bowl"),
        isVegetarian = false,
        isVegan = false,
        isHighProtein = true,
        isLowCarb = false,
        isGlutenFree = false,
        isDairyFree = true,
        isKeto = false,
        rating = 4.8f,
        reviewsCount = 315
    ),

    Recipe(
        id = "rec_35",
        title = "Thai Green Curry",
        description = "A fragrant Thai curry with coconut milk, green curry paste, vegetables, and tender chicken.",
        imageUrl = "https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?auto=format&fit=crop&w=800&q=80",
        cuisine = "Thai",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 15,
        cookTimeMinutes = 25,
        servings = 4,
        difficulty = DifficultyLevel.MEDIUM,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_chicken_breast",
                name = "Chicken Breast",
                amount = 500f,
                unit = "g",
                optional = false,
                substitutions = listOf("Tofu", "Shrimp")
            ),
            RecipeIngredient(
                ingredientId = "ing_coconut_milk",
                name = "Coconut Milk",
                amount = 400f,
                unit = "ml",
                optional = false,
                substitutions = listOf("Light Coconut Milk")
            ),
            RecipeIngredient(
                ingredientId = "ing_green_curry_paste",
                name = "Green Curry Paste",
                amount = 3f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Red Curry Paste")
            ),
            RecipeIngredient(
                ingredientId = "ing_bell_pepper",
                name = "Bell Pepper",
                amount = 1f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Zucchini")
            ),
            RecipeIngredient(
                ingredientId = "ing_basil",
                name = "Thai Basil",
                amount = 20f,
                unit = "g",
                optional = true,
                substitutions = listOf("Sweet Basil")
            ),
            RecipeIngredient(
                ingredientId = "ing_fish_sauce",
                name = "Fish Sauce",
                amount = 1.5f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Soy Sauce")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Heat a little oil and fry the green curry paste until fragrant.",
                timerSeconds = 120,
                tip = "Frying the paste briefly develops its aroma."
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Add coconut milk and bring the curry to a gentle simmer.",
                timerSeconds = 180,
                tip = null
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Add sliced chicken and cook gently until nearly done.",
                timerSeconds = 480,
                tip = "Avoid a hard boil to keep the coconut milk smooth."
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Add bell pepper and fish sauce and simmer until the vegetables are tender-crisp.",
                timerSeconds = 300,
                tip = null
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Stir in Thai basil and serve hot with jasmine rice.",
                timerSeconds = 30,
                tip = null
            )
        ),
        nutrition = NutritionInfo(
            calories = 490,
            proteinGrams = 36,
            carbsGrams = 18,
            fatGrams = 31
        ),
        tags = listOf("Thai", "Curry", "Coconut", "Gluten Free"),
        isVegetarian = false,
        isVegan = false,
        isHighProtein = true,
        isLowCarb = true,
        isGlutenFree = true,
        isDairyFree = true,
        isKeto = false,
        rating = 4.8f,
        reviewsCount = 268
    ),

    Recipe(
        id = "rec_36",
        title = "French Ratatouille",
        description = "A rustic Provençal vegetable stew layered with tomatoes, zucchini, eggplant, peppers, and herbs.",
        imageUrl = "https://images.unsplash.com/photo-1572453800999-e8d2d1589b7c?auto=format&fit=crop&w=800&q=80",
        cuisine = "French",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 20,
        cookTimeMinutes = 45,
        servings = 4,
        difficulty = DifficultyLevel.MEDIUM,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_eggplant",
                name = "Eggplant",
                amount = 300f,
                unit = "g",
                optional = false,
                substitutions = listOf("Zucchini")
            ),
            RecipeIngredient(
                ingredientId = "ing_zucchini",
                name = "Zucchini",
                amount = 300f,
                unit = "g",
                optional = false,
                substitutions = listOf("Yellow Squash")
            ),
            RecipeIngredient(
                ingredientId = "ing_tomatoes",
                name = "Tomatoes",
                amount = 500f,
                unit = "g",
                optional = false,
                substitutions = listOf("Canned Tomatoes")
            ),
            RecipeIngredient(
                ingredientId = "ing_bell_pepper",
                name = "Bell Pepper",
                amount = 2f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Poblano Pepper")
            ),
            RecipeIngredient(
                ingredientId = "ing_olive_oil",
                name = "Olive Oil",
                amount = 4f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Avocado Oil")
            ),
            RecipeIngredient(
                ingredientId = "ing_thyme",
                name = "Fresh Thyme",
                amount = 1f,
                unit = "tbsp",
                optional = true,
                substitutions = listOf("Herbes de Provence")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Cut all vegetables into evenly sized pieces.",
                timerSeconds = null,
                tip = "Uniform pieces help everything cook evenly."
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Sauté eggplant and zucchini separately until lightly browned, then set aside.",
                timerSeconds = 600,
                tip = null
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Sauté onion and bell pepper until softened, then add garlic.",
                timerSeconds = 360,
                tip = null
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Add tomatoes, herbs, eggplant, and zucchini. Cover and simmer gently.",
                timerSeconds = 1500,
                tip = "Slow cooking develops a sweeter, richer flavor."
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Season with salt and pepper and finish with fresh herbs and olive oil.",
                timerSeconds = null,
                tip = null
            )
        ),
        nutrition = NutritionInfo(
            calories = 290,
            proteinGrams = 7,
            carbsGrams = 30,
            fatGrams = 18
        ),
        tags = listOf("French", "Vegetarian", "Vegan", "Mediterranean"),
        isVegetarian = true,
        isVegan = true,
        isHighProtein = false,
        isLowCarb = false,
        isGlutenFree = true,
        isDairyFree = true,
        isKeto = false,
        rating = 4.6f,
        reviewsCount = 198
    ),

    Recipe(
        id = "rec_37",
        title = "Spanish Chicken Paella",
        description = "Saffron-infused Spanish rice cooked with tender chicken, peppers, peas, and aromatic spices.",
        imageUrl = "https://images.unsplash.com/photo-1534080564583-6be75777b70a?auto=format&fit=crop&w=800&q=80",
        cuisine = "Spanish",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 20,
        cookTimeMinutes = 40,
        servings = 4,
        difficulty = DifficultyLevel.HARD,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_chicken_thighs",
                name = "Chicken Thighs",
                amount = 500f,
                unit = "g",
                optional = false,
                substitutions = listOf("Chicken Breast")
            ),
            RecipeIngredient(
                ingredientId = "ing_paella_rice",
                name = "Paella Rice",
                amount = 300f,
                unit = "g",
                optional = false,
                substitutions = listOf("Short Grain Rice")
            ),
            RecipeIngredient(
                ingredientId = "ing_saffron",
                name = "Saffron",
                amount = 0.2f,
                unit = "tsp",
                optional = false,
                substitutions = listOf("Saffron Powder")
            ),
            RecipeIngredient(
                ingredientId = "ing_red_pepper",
                name = "Red Bell Pepper",
                amount = 1f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Yellow Bell Pepper")
            ),
            RecipeIngredient(
                ingredientId = "ing_chicken_stock",
                name = "Chicken Stock",
                amount = 750f,
                unit = "ml",
                optional = false,
                substitutions = listOf("Vegetable Stock")
            ),
            RecipeIngredient(
                ingredientId = "ing_green_peas",
                name = "Green Peas",
                amount = 100f,
                unit = "g",
                optional = false,
                substitutions = listOf("Green Beans")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Season chicken and sear it in a wide paella pan until deeply browned.",
                timerSeconds = 600,
                tip = "A wide pan helps the rice cook in an even layer."
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Sauté onion, garlic, and red pepper until softened.",
                timerSeconds = 300,
                tip = null
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Stir in rice and paprika, coating every grain with the aromatic oil.",
                timerSeconds = 120,
                tip = null
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Add saffron-infused stock and arrange chicken over the rice. Do not stir after this point.",
                timerSeconds = 1200,
                tip = "Leaving the rice undisturbed helps develop the traditional socarrat."
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Add peas near the end and rest the paella before serving with lemon wedges.",
                timerSeconds = 300,
                tip = "Resting lets the rice finish absorbing the broth."
            )
        ),
        nutrition = NutritionInfo(
            calories = 590,
            proteinGrams = 39,
            carbsGrams = 69,
            fatGrams = 16
        ),
        tags = listOf("Spanish", "Paella", "One Pan", "Authentic"),
        isVegetarian = false,
        isVegan = false,
        isHighProtein = true,
        isLowCarb = false,
        isGlutenFree = true,
        isDairyFree = true,
        isKeto = false,
        rating = 4.9f,
        reviewsCount = 376
    ),

    Recipe(
        id = "rec_38",
        title = "Greek Chicken Souvlaki",
        description = "Lemony Greek chicken skewers marinated with garlic, oregano, olive oil, and served with fresh salad.",
        imageUrl = "https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=800&q=80",
        cuisine = "Greek",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 20,
        cookTimeMinutes = 15,
        servings = 4,
        difficulty = DifficultyLevel.EASY,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_chicken_breast",
                name = "Chicken Breast",
                amount = 600f,
                unit = "g",
                optional = false,
                substitutions = listOf("Chicken Thighs", "Tofu")
            ),
            RecipeIngredient(
                ingredientId = "ing_olive_oil",
                name = "Olive Oil",
                amount = 3f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Avocado Oil")
            ),
            RecipeIngredient(
                ingredientId = "ing_lemon",
                name = "Lemon Juice",
                amount = 3f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("White Wine Vinegar")
            ),
            RecipeIngredient(
                ingredientId = "ing_dried_oregano",
                name = "Dried Oregano",
                amount = 2f,
                unit = "tsp",
                optional = false,
                substitutions = listOf("Fresh Oregano")
            ),
            RecipeIngredient(
                ingredientId = "ing_garlic",
                name = "Garlic",
                amount = 3f,
                unit = "cloves",
                optional = false,
                substitutions = listOf("Garlic Paste")
            ),
            RecipeIngredient(
                ingredientId = "ing_pita",
                name = "Pita Bread",
                amount = 4f,
                unit = "pcs",
                optional = true,
                substitutions = listOf("Flatbread", "Lettuce Wraps")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Combine olive oil, lemon juice, garlic, oregano, salt, and pepper.",
                timerSeconds = null,
                tip = null
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Add chicken pieces and marinate thoroughly.",
                timerSeconds = 1800,
                tip = "Longer marination gives the chicken more flavor."
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Thread chicken onto skewers and grill over high heat until browned and cooked through.",
                timerSeconds = 720,
                tip = "Turn the skewers regularly for even browning."
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Warm pita bread and prepare tomato, cucumber, onion, and tzatziki.",
                timerSeconds = 120,
                tip = null
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Serve chicken skewers with pita, tzatziki, salad, and lemon.",
                timerSeconds = null,
                tip = null
            )
        ),
        nutrition = NutritionInfo(
            calories = 480,
            proteinGrams = 45,
            carbsGrams = 31,
            fatGrams = 20
        ),
        tags = listOf("Greek", "Mediterranean", "High Protein", "Grilled"),
        isVegetarian = false,
        isVegan = false,
        isHighProtein = true,
        isLowCarb = false,
        isGlutenFree = false,
        isDairyFree = false,
        isKeto = false,
        rating = 4.8f,
        reviewsCount = 251
    ),

    Recipe(
        id = "rec_39",
        title = "Kung Pao Chicken",
        description = "A Sichuan-inspired stir-fry featuring tender chicken, dried chilies, peanuts, and a savory tangy sauce.",
        imageUrl = "https://images.unsplash.com/photo-1603133872878-684f208fb84b?auto=format&fit=crop&w=800&q=80",
        cuisine = "Chinese",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 20,
        cookTimeMinutes = 15,
        servings = 3,
        difficulty = DifficultyLevel.MEDIUM,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_chicken_breast",
                name = "Chicken Breast",
                amount = 450f,
                unit = "g",
                optional = false,
                substitutions = listOf("Chicken Thighs", "Tofu")
            ),
            RecipeIngredient(
                ingredientId = "ing_peanuts",
                name = "Roasted Peanuts",
                amount = 80f,
                unit = "g",
                optional = false,
                substitutions = listOf("Cashews")
            ),
            RecipeIngredient(
                ingredientId = "ing_dried_chili",
                name = "Dried Red Chilies",
                amount = 8f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Fresh Red Chili")
            ),
            RecipeIngredient(
                ingredientId = "ing_soy_sauce",
                name = "Soy Sauce",
                amount = 2f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Tamari")
            ),
            RecipeIngredient(
                ingredientId = "ing_rice_vinegar",
                name = "Rice Vinegar",
                amount = 1f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Apple Cider Vinegar")
            ),
            RecipeIngredient(
                ingredientId = "ing_sichuan_peppercorn",
                name = "Sichuan Peppercorns",
                amount = 1f,
                unit = "tsp",
                optional = true,
                substitutions = listOf("Black Pepper")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Cut chicken into bite-sized pieces and toss with soy sauce and cornstarch.",
                timerSeconds = 600,
                tip = null
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Mix soy sauce, rice vinegar, sugar, and a little water for the stir-fry sauce.",
                timerSeconds = null,
                tip = null
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Heat a wok until very hot and stir-fry chicken until lightly browned.",
                timerSeconds = 300,
                tip = "Work in batches if necessary."
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Add dried chilies, Sichuan peppercorns, garlic, and ginger and stir-fry until fragrant.",
                timerSeconds = 90,
                tip = "Keep the chilies moving so they do not burn."
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Return chicken to the wok, add sauce and peanuts, and toss until glossy.",
                timerSeconds = 120,
                tip = null
            )
        ),
        nutrition = NutritionInfo(
            calories = 520,
            proteinGrams = 42,
            carbsGrams = 25,
            fatGrams = 27
        ),
        tags = listOf("Chinese", "Sichuan", "Spicy", "Stir Fry"),
        isVegetarian = false,
        isVegan = false,
        isHighProtein = true,
        isLowCarb = true,
        isGlutenFree = false,
        isDairyFree = true,
        isKeto = false,
        rating = 4.7f,
        reviewsCount = 219
    ),

    Recipe(
        id = "rec_40",
        title = "Chicken Shawarma Plate",
        description = "Middle Eastern spiced chicken served with creamy tahini, fresh vegetables, herbs, and warm flatbread.",
        imageUrl = "https://images.unsplash.com/photo-1529006557810-274b9b2fc783?auto=format&fit=crop&w=800&q=80",
        cuisine = "Middle Eastern",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 20,
        cookTimeMinutes = 25,
        servings = 4,
        difficulty = DifficultyLevel.MEDIUM,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_chicken_thighs",
                name = "Chicken Thighs",
                amount = 600f,
                unit = "g",
                optional = false,
                substitutions = listOf("Chicken Breast")
            ),
            RecipeIngredient(
                ingredientId = "ing_yogurt",
                name = "Plain Yogurt",
                amount = 100f,
                unit = "g",
                optional = false,
                substitutions = listOf("Dairy-Free Yogurt")
            ),
            RecipeIngredient(
                ingredientId = "ing_tahini",
                name = "Tahini",
                amount = 3f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Hummus")
            ),
            RecipeIngredient(
                ingredientId = "ing_lemon",
                name = "Lemon Juice",
                amount = 3f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Lime Juice")
            ),
            RecipeIngredient(
                ingredientId = "ing_cumin",
                name = "Ground Cumin",
                amount = 2f,
                unit = "tsp",
                optional = false,
                substitutions = listOf("Coriander Powder")
            ),
            RecipeIngredient(
                ingredientId = "ing_paprika",
                name = "Paprika",
                amount = 2f,
                unit = "tsp",
                optional = false,
                substitutions = listOf("Smoked Paprika")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Mix yogurt, lemon juice, garlic, cumin, paprika, coriander, and salt.",
                timerSeconds = null,
                tip = null
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Coat chicken thoroughly with the marinade and refrigerate.",
                timerSeconds = 1800,
                tip = "For maximum flavor, marinate overnight."
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Roast or pan-sear chicken over high heat until browned and cooked through.",
                timerSeconds = 900,
                tip = "Let the edges char slightly for a shawarma-style flavor."
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Whisk tahini with lemon juice, water, and salt to make a smooth sauce.",
                timerSeconds = null,
                tip = null
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Slice chicken and serve with tahini, cucumber, tomato, herbs, and warm flatbread.",
                timerSeconds = null,
                tip = null
            )
        ),
        nutrition = NutritionInfo(
            calories = 510,
            proteinGrams = 44,
            carbsGrams = 24,
            fatGrams = 27
        ),
        tags = listOf("Middle Eastern", "Shawarma", "High Protein", "Street Food"),
        isVegetarian = false,
        isVegan = false,
        isHighProtein = true,
        isLowCarb = true,
        isGlutenFree = true,
        isDairyFree = false,
        isKeto = false,
        rating = 4.9f,
        reviewsCount = 389
    ),

    Recipe(
        id = "rec_41",
        title = "Classic American Cheeseburger",
        description = "A juicy beef patty layered with melted cheddar, crisp lettuce, tomato, onion, and a toasted bun.",
        imageUrl = "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=800&q=80",
        cuisine = "American",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 10,
        cookTimeMinutes = 15,
        servings = 2,
        difficulty = DifficultyLevel.EASY,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_ground_beef",
                name = "Ground Beef",
                amount = 300f,
                unit = "g",
                optional = false,
                substitutions = listOf("Ground Turkey", "Plant-Based Patty")
            ),
            RecipeIngredient(
                ingredientId = "ing_burger_buns",
                name = "Burger Buns",
                amount = 2f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Lettuce Wraps")
            ),
            RecipeIngredient(
                ingredientId = "ing_cheddar",
                name = "Cheddar Cheese",
                amount = 2f,
                unit = "slices",
                optional = false,
                substitutions = listOf("American Cheese", "Vegan Cheese")
            ),
            RecipeIngredient(
                ingredientId = "ing_lettuce",
                name = "Lettuce",
                amount = 2f,
                unit = "slices",
                optional = false,
                substitutions = listOf("Arugula")
            ),
            RecipeIngredient(
                ingredientId = "ing_tomato",
                name = "Tomato",
                amount = 1f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Pickles")
            ),
            RecipeIngredient(
                ingredientId = "ing_onion",
                name = "Onion",
                amount = 0.5f,
                unit = "pcs",
                optional = true,
                substitutions = listOf("Pickled Onion")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Divide beef into two portions and gently shape into patties.",
                timerSeconds = null,
                tip = "Do not overwork the meat."
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Season generously with salt and pepper.",
                timerSeconds = null,
                tip = null
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Sear patties in a very hot skillet until browned and cooked to your preferred doneness.",
                timerSeconds = 480,
                tip = "Avoid pressing the patties while cooking."
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Place cheddar on the patties and cover briefly until melted.",
                timerSeconds = 60,
                tip = null
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Toast buns and assemble with lettuce, tomato, onion, and burger sauce.",
                timerSeconds = 120,
                tip = null
            )
        ),
        nutrition = NutritionInfo(
            calories = 680,
            proteinGrams = 42,
            carbsGrams = 43,
            fatGrams = 38
        ),
        tags = listOf("American", "Burger", "Comfort Food", "High Protein"),
        isVegetarian = false,
        isVegan = false,
        isHighProtein = true,
        isLowCarb = false,
        isGlutenFree = false,
        isDairyFree = false,
        isKeto = false,
        rating = 4.7f,
        reviewsCount = 512
    ),

    Recipe(
        id = "rec_42",
        title = "Vietnamese Pho Ga",
        description = "A fragrant Vietnamese chicken noodle soup with aromatic broth, rice noodles, herbs, and fresh lime.",
        imageUrl = "https://images.unsplash.com/photo-1582878826629-29b7ad1cdc43?auto=format&fit=crop&w=800&q=80",
        cuisine = "Vietnamese",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 20,
        cookTimeMinutes = 60,
        servings = 4,
        difficulty = DifficultyLevel.HARD,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_chicken",
                name = "Whole Chicken",
                amount = 1.2f,
                unit = "kg",
                optional = false,
                substitutions = listOf("Chicken Thighs")
            ),
            RecipeIngredient(
                ingredientId = "ing_rice_noodles",
                name = "Rice Noodles",
                amount = 300f,
                unit = "g",
                optional = false,
                substitutions = listOf("Flat Rice Noodles")
            ),
            RecipeIngredient(
                ingredientId = "ing_onion",
                name = "Onion",
                amount = 1f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Shallots")
            ),
            RecipeIngredient(
                ingredientId = "ing_ginger",
                name = "Fresh Ginger",
                amount = 60f,
                unit = "g",
                optional = false,
                substitutions = listOf("Ginger Paste")
            ),
            RecipeIngredient(
                ingredientId = "ing_star_anise",
                name = "Star Anise",
                amount = 3f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Five Spice")
            ),
            RecipeIngredient(
                ingredientId = "ing_fish_sauce",
                name = "Fish Sauce",
                amount = 2f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Soy Sauce")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Char onion and ginger over an open flame or dry skillet until deeply browned.",
                timerSeconds = 300,
                tip = "The charred aromatics give pho its distinctive depth."
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Simmer chicken with water, onion, ginger, star anise, cinnamon, and salt.",
                timerSeconds = 2400,
                tip = "Skim the surface regularly for a clearer broth."
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Remove chicken, shred the meat, and strain the broth.",
                timerSeconds = null,
                tip = null
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Cook rice noodles according to package instructions and drain.",
                timerSeconds = 240,
                tip = "Do not overcook the noodles."
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Place noodles and chicken in bowls, pour over hot broth, and finish with herbs, lime, and chili.",
                timerSeconds = null,
                tip = "Serve immediately while the broth is piping hot."
            )
        ),
        nutrition = NutritionInfo(
            calories = 420,
            proteinGrams = 38,
            carbsGrams = 46,
            fatGrams = 11
        ),
        tags = listOf("Vietnamese", "Soup", "Comfort Food", "High Protein"),
        isVegetarian = false,
        isVegan = false,
        isHighProtein = true,
        isLowCarb = false,
        isGlutenFree = true,
        isDairyFree = true,
        isKeto = false,
        rating = 4.9f,
        reviewsCount = 334
    ),

    Recipe(
        id = "rec_43",
        title = "Korean Bibimbap",
        description = "A colorful Korean rice bowl topped with seasoned vegetables, fried egg, gochujang, and sesame.",
        imageUrl = "https://images.unsplash.com/photo-1553163147-622ab57be1c7?auto=format&fit=crop&w=800&q=80",
        cuisine = "Korean",
        mealTypes = listOf(MealType.LUNCH, MealType.DINNER),
        prepTimeMinutes = 25,
        cookTimeMinutes = 20,
        servings = 2,
        difficulty = DifficultyLevel.MEDIUM,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_rice",
                name = "Cooked Short Grain Rice",
                amount = 350f,
                unit = "g",
                optional = false,
                substitutions = listOf("Brown Rice")
            ),
            RecipeIngredient(
                ingredientId = "ing_beef",
                name = "Thinly Sliced Beef",
                amount = 250f,
                unit = "g",
                optional = false,
                substitutions = listOf("Tofu", "Chicken")
            ),
            RecipeIngredient(
                ingredientId = "ing_spinach",
                name = "Spinach",
                amount = 150f,
                unit = "g",
                optional = false,
                substitutions = listOf("Kale")
            ),
            RecipeIngredient(
                ingredientId = "ing_carrot",
                name = "Carrot",
                amount = 1f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Bell Pepper")
            ),
            RecipeIngredient(
                ingredientId = "ing_mushrooms",
                name = "Mushrooms",
                amount = 150f,
                unit = "g",
                optional = false,
                substitutions = listOf("Zucchini")
            ),
            RecipeIngredient(
                ingredientId = "ing_gochujang",
                name = "Gochujang",
                amount = 2f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Sriracha")
            ),
            RecipeIngredient(
                ingredientId = "ing_egg",
                name = "Egg",
                amount = 2f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Tofu")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Season beef with soy sauce, sesame oil, garlic, and a little sugar.",
                timerSeconds = 600,
                tip = null
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Cook spinach briefly, squeeze out excess water, and season with sesame oil and salt.",
                timerSeconds = 120,
                tip = null
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Sauté carrot and mushrooms separately until tender but still slightly crisp.",
                timerSeconds = 360,
                tip = "Cooking vegetables separately preserves their individual textures."
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Stir-fry marinated beef until browned and cooked through.",
                timerSeconds = 300,
                tip = null
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Arrange rice, vegetables, beef, and a fried egg in bowls. Add gochujang and sesame seeds.",
                timerSeconds = 180,
                tip = "Mix everything together just before eating."
            )
        ),
        nutrition = NutritionInfo(
            calories = 610,
            proteinGrams = 38,
            carbsGrams = 68,
            fatGrams = 21
        ),
        tags = listOf("Korean", "Rice Bowl", "High Protein", "Colorful"),
        isVegetarian = false,
        isVegan = false,
        isHighProtein = true,
        isLowCarb = false,
        isGlutenFree = false,
        isDairyFree = true,
        isKeto = false,
        rating = 4.9f,
        reviewsCount = 294
    ),

    Recipe(
        id = "rec_44",
        title = "Avocado Toast with Poached Egg",
        description = "Creamy smashed avocado on crisp toast topped with a perfectly poached egg and chili flakes.",
        imageUrl = "https://images.unsplash.com/photo-1541519227354-08fa5d50c44d?auto=format&fit=crop&w=800&q=80",
        cuisine = "American",
        mealTypes = listOf(MealType.BREAKFAST, MealType.BRUNCH),
        prepTimeMinutes = 10,
        cookTimeMinutes = 8,
        servings = 2,
        difficulty = DifficultyLevel.EASY,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_avocado",
                name = "Avocado",
                amount = 2f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Hummus")
            ),
            RecipeIngredient(
                ingredientId = "ing_sourdough",
                name = "Sourdough Bread",
                amount = 2f,
                unit = "slices",
                optional = false,
                substitutions = listOf("Whole Grain Bread", "Gluten-Free Bread")
            ),
            RecipeIngredient(
                ingredientId = "ing_eggs",
                name = "Eggs",
                amount = 2f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Tofu")
            ),
            RecipeIngredient(
                ingredientId = "ing_chili_flakes",
                name = "Chili Flakes",
                amount = 0.5f,
                unit = "tsp",
                optional = true,
                substitutions = listOf("Black Pepper")
            ),
            RecipeIngredient(
                ingredientId = "ing_lemon",
                name = "Lemon Juice",
                amount = 1f,
                unit = "tbsp",
                optional = false,
                substitutions = listOf("Lime Juice")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Toast sourdough until crisp and golden.",
                timerSeconds = 180,
                tip = null
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Mash avocado with lemon juice, salt, and pepper.",
                timerSeconds = null,
                tip = "Leave some small chunks for texture."
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Poach eggs in gently simmering water until the whites are set and yolks remain runny.",
                timerSeconds = 210,
                tip = "A fresh egg holds together better while poaching."
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Spread avocado over toast and top with poached eggs and chili flakes.",
                timerSeconds = null,
                tip = null
            )
        ),
        nutrition = NutritionInfo(
            calories = 390,
            proteinGrams = 15,
            carbsGrams = 32,
            fatGrams = 25
        ),
        tags = listOf("Breakfast", "Brunch", "Vegetarian", "Quick"),
        isVegetarian = true,
        isVegan = false,
        isHighProtein = false,
        isLowCarb = false,
        isGlutenFree = false,
        isDairyFree = true,
        isKeto = false,
        rating = 4.6f,
        reviewsCount = 185
    ),

    Recipe(
        id = "rec_45",
        title = "Mango Sticky Rice",
        description = "A beloved Thai dessert pairing sweet coconut-infused sticky rice with ripe juicy mango.",
        imageUrl = "https://images.unsplash.com/photo-1551024506-0bccd828d307?auto=format&fit=crop&w=800&q=80",
        cuisine = "Thai",
        mealTypes = listOf(MealType.DESSERT),
        prepTimeMinutes = 15,
        cookTimeMinutes = 30,
        servings = 4,
        difficulty = DifficultyLevel.MEDIUM,
        ingredients = listOf(
            RecipeIngredient(
                ingredientId = "ing_sticky_rice",
                name = "Glutinous Rice",
                amount = 250f,
                unit = "g",
                optional = false,
                substitutions = listOf("Thai Sticky Rice")
            ),
            RecipeIngredient(
                ingredientId = "ing_coconut_milk",
                name = "Coconut Milk",
                amount = 400f,
                unit = "ml",
                optional = false,
                substitutions = listOf("Coconut Cream")
            ),
            RecipeIngredient(
                ingredientId = "ing_mango",
                name = "Ripe Mango",
                amount = 2f,
                unit = "pcs",
                optional = false,
                substitutions = listOf("Peach")
            ),
            RecipeIngredient(
                ingredientId = "ing_sugar",
                name = "Sugar",
                amount = 80f,
                unit = "g",
                optional = false,
                substitutions = listOf("Coconut Sugar")
            ),
            RecipeIngredient(
                ingredientId = "ing_sesame_seeds",
                name = "Sesame Seeds",
                amount = 1f,
                unit = "tbsp",
                optional = true,
                substitutions = listOf("Toasted Coconut")
            )
        ),
        steps = listOf(
            RecipeStep(
                stepNumber = 1,
                instruction = "Rinse glutinous rice until the water runs mostly clear, then soak.",
                timerSeconds = 1200,
                tip = "Soaking helps the rice steam evenly."
            ),
            RecipeStep(
                stepNumber = 2,
                instruction = "Steam the soaked rice until tender and translucent.",
                timerSeconds = 1200,
                tip = null
            ),
            RecipeStep(
                stepNumber = 3,
                instruction = "Warm coconut milk with sugar and salt without allowing it to boil.",
                timerSeconds = 180,
                tip = null
            ),
            RecipeStep(
                stepNumber = 4,
                instruction = "Pour most of the warm coconut mixture over the sticky rice and let it absorb.",
                timerSeconds = 600,
                tip = "Cover the rice while it absorbs the coconut milk."
            ),
            RecipeStep(
                stepNumber = 5,
                instruction = "Serve sticky rice with sliced ripe mango and drizzle with the remaining coconut sauce.",
                timerSeconds = null,
                tip = "Serve slightly warm or at room temperature."
            )
        ),
        nutrition = NutritionInfo(
            calories = 430,
            proteinGrams = 5,
            carbsGrams = 76,
            fatGrams = 13
        ),
        tags = listOf("Thai", "Dessert", "Vegan", "Gluten Free"),
        isVegetarian = true,
        isVegan = true,
        isHighProtein = false,
        isLowCarb = false,
        isGlutenFree = true,
        isDairyFree = true,
        isKeto = false,
        rating = 4.8f,
        reviewsCount = 276
    )
    )
}
