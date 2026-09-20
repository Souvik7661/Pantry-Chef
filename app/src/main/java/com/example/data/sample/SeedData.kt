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
        Ingredient("ing_chicken_broth", "Chicken Broth", IngredientCategory.PANTRY, "ml", 15)
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
        )
    )
}
