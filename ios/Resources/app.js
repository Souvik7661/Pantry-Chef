/**
 * PantryChef Web Application Engine
 * Interactive Kitchen, Recipe Matcher, Step-by-Step Cooking & AI Assistant
 */

// 1. DATA CATALOG (Ported from SeedData.kt)
const RECIPES_DATA = [
  {
    id: "rec_01",
    title: "Creamy Garlic Chicken",
    description: "Pan-seared chicken breast simmered in a velvety garlic and herb cream sauce with fresh wilted spinach.",
    imageUrl: "https://images.unsplash.com/photo-1604908176997-125f25cc6f3d?auto=format&fit=crop&w=800&q=80",
    cuisine: "Italian",
    mealTypes: ["Dinner", "Lunch"],
    prepTimeMinutes: 10,
    cookTimeMinutes: 25,
    servings: 4,
    difficulty: "Medium",
    rating: 4.9,
    reviewsCount: 342,
    nutrition: { calories: 480, protein: 46, carbs: 6, fat: 30 },
    tags: ["High Protein", "Low Carb", "Keto", "Quick Dinners"],
    ingredients: [
      { name: "Chicken Breast", amount: 500, unit: "g" },
      { name: "Garlic", amount: 5, unit: "cloves" },
      { name: "Spinach", amount: 100, unit: "g" },
      { name: "Heavy Cream", amount: 200, unit: "ml" },
      { name: "Parmesan Cheese", amount: 50, unit: "g" },
      { name: "Olive Oil", amount: 2, unit: "tbsp" },
      { name: "Salt", amount: 1, unit: "tsp" },
      { name: "Black Pepper", amount: 1, unit: "tsp" }
    ],
    steps: [
      { stepNumber: 1, text: "Season chicken breasts generously on both sides with salt and black pepper.", timer: 0 },
      { stepNumber: 2, text: "Heat 2 tbsp olive oil in a large skillet over medium-high heat.", timer: 120 },
      { stepNumber: 3, text: "Add chicken breasts and sear until golden brown on both sides (about 6-7 mins per side). Transfer to a warm plate.", timer: 420 },
      { stepNumber: 4, text: "In the same pan, reduce heat to medium and sauté minced garlic for 1 minute until fragrant.", timer: 60 },
      { stepNumber: 5, text: "Pour in heavy cream and scrape browned bits from the pan bottom. Bring to a gentle simmer.", timer: 180 },
      { stepNumber: 6, text: "Stir in parmesan cheese and fresh spinach until wilted and rich.", timer: 120 },
      { stepNumber: 7, text: "Return chicken to the skillet, spoon sauce over top, and simmer for 3 minutes before serving.", timer: 180 }
    ]
  },
  {
    id: "rec_02",
    title: "Chicken Tomato Rice Skillet",
    description: "A comforting one-pot meal featuring golden chicken, fragrant basmati rice, tender onions, and sweet blistered tomatoes.",
    imageUrl: "https://images.unsplash.com/photo-1512058564366-18510be2db19?auto=format&fit=crop&w=800&q=80",
    cuisine: "Mediterranean",
    mealTypes: ["Dinner", "Lunch"],
    prepTimeMinutes: 8,
    cookTimeMinutes: 20,
    servings: 3,
    difficulty: "Easy",
    rating: 4.8,
    reviewsCount: 215,
    nutrition: { calories: 420, protein: 38, carbs: 48, fat: 9 },
    tags: ["One Pot", "Under 30 Minutes", "High Protein", "Family Favorite"],
    ingredients: [
      { name: "Chicken Breast", amount: 400, unit: "g" },
      { name: "Basmati Rice", amount: 200, unit: "g" },
      { name: "Tomato", amount: 3, unit: "pcs" },
      { name: "Onion", amount: 1, unit: "pcs" },
      { name: "Garlic", amount: 3, unit: "cloves" },
      { name: "Olive Oil", amount: 2, unit: "tbsp" },
      { name: "Chicken Broth", amount: 400, unit: "ml" },
      { name: "Salt", amount: 1, unit: "tsp" }
    ],
    steps: [
      { stepNumber: 1, text: "Dice chicken, onion, and fresh tomatoes into bite-sized cubes.", timer: 0 },
      { stepNumber: 2, text: "Heat olive oil in a skillet and brown chicken cubes for 4 minutes until golden.", timer: 240 },
      { stepNumber: 3, text: "Add chopped onion and minced garlic, cook for 2 minutes until translucent.", timer: 120 },
      { stepNumber: 4, text: "Add dry basmati rice and toast lightly for 1 minute.", timer: 60 },
      { stepNumber: 5, text: "Pour chicken broth and chopped tomatoes over the rice. Cover and simmer on low for 15 minutes.", timer: 900 },
      { stepNumber: 6, text: "Remove lid, fluff rice with a fork, and let rest 2 minutes before serving.", timer: 120 }
    ]
  },
  {
    id: "rec_03",
    title: "Classic Fluffy French Omelette",
    description: "Silky, tender eggs folded with cheddar cheese, fresh herbs, and butter. The ultimate quick pantry meal.",
    imageUrl: "https://images.unsplash.com/photo-1510693206972-df098062cb71?auto=format&fit=crop&w=800&q=80",
    cuisine: "French",
    mealTypes: ["Breakfast", "Lunch"],
    prepTimeMinutes: 3,
    cookTimeMinutes: 7,
    servings: 1,
    difficulty: "Easy",
    rating: 4.7,
    reviewsCount: 180,
    nutrition: { calories: 310, protein: 22, carbs: 2, fat: 24 },
    tags: ["Quick & Easy", "Under 15 Minutes", "Vegetarian", "Keto", "High Protein"],
    ingredients: [
      { name: "Eggs", amount: 3, unit: "pcs" },
      { name: "Butter", amount: 15, unit: "g" },
      { name: "Cheddar Cheese", amount: 30, unit: "g" },
      { name: "Salt", amount: 0.5, unit: "tsp" },
      { name: "Black Pepper", amount: 0.5, unit: "tsp" }
    ],
    steps: [
      { stepNumber: 1, text: "Whisk eggs with salt and black pepper until completely smooth and aerated.", timer: 0 },
      { stepNumber: 2, text: "Melt butter in a non-stick skillet over medium-low heat until frothy.", timer: 60 },
      { stepNumber: 3, text: "Pour in eggs and shake pan continuously while swirling with a rubber spatula for 2 minutes.", timer: 120 },
      { stepNumber: 4, text: "Sprinkle cheese along the center, gently fold edges over, and slide onto a warm plate.", timer: 60 }
    ]
  },
  {
    id: "rec_04",
    title: "Quick Garlic Butter Spaghetti",
    description: "A classic Aglio e Olio with al dente spaghetti tossed in golden garlic slices, olive oil, red pepper flakes, and parmesan.",
    imageUrl: "https://images.unsplash.com/photo-1551183053-bf91a1d81141?auto=format&fit=crop&w=800&q=80",
    cuisine: "Italian",
    mealTypes: ["Dinner", "Lunch"],
    prepTimeMinutes: 5,
    cookTimeMinutes: 12,
    servings: 2,
    difficulty: "Easy",
    rating: 4.9,
    reviewsCount: 410,
    nutrition: { calories: 490, protein: 14, carbs: 72, fat: 17 },
    tags: ["Budget Friendly", "Vegetarian", "Quick & Easy", "Pantry Staples"],
    ingredients: [
      { name: "Spaghetti", amount: 200, unit: "g" },
      { name: "Garlic", amount: 6, unit: "cloves" },
      { name: "Olive Oil", amount: 3, unit: "tbsp" },
      { name: "Red Chili Flakes", amount: 1, unit: "tsp" },
      { name: "Parmesan Cheese", amount: 30, unit: "g" },
      { name: "Salt", amount: 1, unit: "tsp" }
    ],
    steps: [
      { stepNumber: 1, text: "Boil heavily salted water and cook spaghetti for 8-9 minutes until al dente.", timer: 540 },
      { stepNumber: 2, text: "Meanwhile, thinly slice garlic cloves.", timer: 0 },
      { stepNumber: 3, text: "Heat olive oil in a pan over low heat; add garlic and red chili flakes. Sauté gently until pale golden.", timer: 180 },
      { stepNumber: 4, text: "Reserve 1/2 cup pasta water, drain pasta, and toss spaghetti directly into the garlic oil.", timer: 60 },
      { stepNumber: 5, text: "Add pasta water and vigorously toss until a glossy emulsion coats every strand. Top with grated parmesan.", timer: 60 }
    ]
  },
  {
    id: "rec_05",
    title: "Aloo Jeera (Spiced Cumin Potatoes)",
    description: "Crispy golden cubed potatoes tempered with roasted cumin seeds, turmeric, green chili, and fresh cilantro.",
    imageUrl: "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?auto=format&fit=crop&w=800&q=80",
    cuisine: "Indian",
    mealTypes: ["Lunch", "Dinner"],
    prepTimeMinutes: 10,
    cookTimeMinutes: 15,
    servings: 3,
    difficulty: "Easy",
    rating: 4.8,
    reviewsCount: 190,
    nutrition: { calories: 230, protein: 5, carbs: 38, fat: 8 },
    tags: ["Indian Favorites", "Vegetarian", "Vegan", "Budget Friendly", "Gluten Free"],
    ingredients: [
      { name: "Potato", amount: 4, unit: "pcs" },
      { name: "Cumin Seeds", amount: 2, unit: "tsp" },
      { name: "Turmeric Powder", amount: 0.5, unit: "tsp" },
      { name: "Green Chili", amount: 2, unit: "pcs" },
      { name: "Vegetable Oil", amount: 2, unit: "tbsp" },
      { name: "Salt", amount: 1, unit: "tsp" }
    ],
    steps: [
      { stepNumber: 1, text: "Boil or microwave potatoes until tender, then peel and dice into 1-inch cubes.", timer: 0 },
      { stepNumber: 2, text: "Heat vegetable oil in a pan and add cumin seeds; let them crackle and release aroma.", timer: 45 },
      { stepNumber: 3, text: "Add chopped green chili and turmeric powder, stirring quickly for 15 seconds.", timer: 15 },
      { stepNumber: 4, text: "Toss in potato cubes and salt. Sauté on medium-high heat for 8 minutes until crisp edges form.", timer: 480 },
      { stepNumber: 5, text: "Garnish with chopped fresh cilantro and serve hot.", timer: 0 }
    ]
  },
  {
    id: "rec_06",
    title: "Paneer Butter Masala",
    description: "Succulent paneer cubes simmered in a silky tomato, onion, and cashew gravy infused with aromatic spices.",
    imageUrl: "https://images.unsplash.com/photo-1631452180519-c014fe946bc7?auto=format&fit=crop&w=800&q=80",
    cuisine: "Indian",
    mealTypes: ["Dinner", "Lunch"],
    prepTimeMinutes: 12,
    cookTimeMinutes: 20,
    servings: 4,
    difficulty: "Medium",
    rating: 4.9,
    reviewsCount: 520,
    nutrition: { calories: 390, protein: 18, carbs: 14, fat: 31 },
    tags: ["Indian Favorites", "Vegetarian", "High Protein"],
    ingredients: [
      { name: "Paneer", amount: 300, unit: "g" },
      { name: "Tomato", amount: 4, unit: "pcs" },
      { name: "Onion", amount: 2, unit: "pcs" },
      { name: "Garlic", amount: 4, unit: "cloves" },
      { name: "Ginger", amount: 15, unit: "g" },
      { name: "Butter", amount: 25, unit: "g" },
      { name: "Heavy Cream", amount: 50, unit: "ml" },
      { name: "Garam Masala", amount: 1, unit: "tsp" }
    ],
    steps: [
      { stepNumber: 1, text: "Roughly chop tomatoes and onions. Sauté in 1 tbsp butter with garlic and ginger for 6 minutes.", timer: 360 },
      { stepNumber: 2, text: "Blend the sautéed tomato-onion mixture into a velvety smooth puree.", timer: 0 },
      { stepNumber: 3, text: "Heat remaining butter in pan, add pureed sauce, garam masala, and salt.", timer: 240 },
      { stepNumber: 4, text: "Add paneer cubes and simmer gently for 5 minutes.", timer: 300 },
      { stepNumber: 5, text: "Swirl in heavy cream and serve with warm rice or naan.", timer: 60 }
    ]
  },
  {
    id: "rec_07",
    title: "Honey Garlic Glazed Salmon",
    description: "Pan-crisped salmon fillets glazed in a sticky, savory reduction of honey, soy sauce, and fresh garlic.",
    imageUrl: "https://images.unsplash.com/photo-1467003909585-2f8a72700288?auto=format&fit=crop&w=800&q=80",
    cuisine: "Asian",
    mealTypes: ["Dinner"],
    prepTimeMinutes: 5,
    cookTimeMinutes: 12,
    servings: 2,
    difficulty: "Easy",
    rating: 4.9,
    reviewsCount: 280,
    nutrition: { calories: 380, protein: 35, carbs: 18, fat: 19 },
    tags: ["High Protein", "Under 20 Minutes", "Pescatarian"],
    ingredients: [
      { name: "Salmon Fillet", amount: 350, unit: "g" },
      { name: "Honey", amount: 2, unit: "tbsp" },
      { name: "Soy Sauce", amount: 2, unit: "tbsp" },
      { name: "Garlic", amount: 3, unit: "cloves" },
      { name: "Lemon", amount: 1, unit: "pcs" },
      { name: "Olive Oil", amount: 1, unit: "tbsp" }
    ],
    steps: [
      { stepNumber: 1, text: "Pat salmon fillets dry with paper towels and season with a pinch of salt and pepper.", timer: 0 },
      { stepNumber: 2, text: "In a small bowl, whisk together honey, soy sauce, minced garlic, and 1 tbsp lemon juice.", timer: 0 },
      { stepNumber: 3, text: "Heat olive oil in a skillet over medium-high heat. Place salmon skin-side up and sear for 4 minutes.", timer: 240 },
      { stepNumber: 4, text: "Flip salmon carefully, pour glaze over fish, and baste continuously for 3-4 minutes until sauce bubbles and thickens.", timer: 210 },
      { stepNumber: 5, text: "Serve immediately with extra pan glaze drizzled over top.", timer: 30 }
    ]
  },
  {
    id: "rec_08",
    title: "Loaded Breakfast Scramble",
    description: "Fluffy scrambled eggs folded with diced potatoes, caramelized onions, sweet tomatoes, and melted cheese.",
    imageUrl: "https://images.unsplash.com/photo-1525351484163-7529414344d8?auto=format&fit=crop&w=800&q=80",
    cuisine: "American",
    mealTypes: ["Breakfast", "Lunch"],
    prepTimeMinutes: 5,
    cookTimeMinutes: 10,
    servings: 2,
    difficulty: "Easy",
    rating: 4.7,
    reviewsCount: 145,
    nutrition: { calories: 340, protein: 20, carbs: 22, fat: 20 },
    tags: ["Breakfast", "Under 15 Minutes", "Vegetarian", "High Protein"],
    ingredients: [
      { name: "Eggs", amount: 4, unit: "pcs" },
      { name: "Potato", amount: 1, unit: "pcs" },
      { name: "Onion", amount: 0.5, unit: "pcs" },
      { name: "Tomato", amount: 1, unit: "pcs" },
      { name: "Cheddar Cheese", amount: 40, unit: "g" },
      { name: "Butter", amount: 1, unit: "tbsp" }
    ],
    steps: [
      { stepNumber: 1, text: "Dice potato into tiny 1/2 cm cubes so they cook rapidly. Dice onion and tomato.", timer: 0 },
      { stepNumber: 2, text: "Melt butter in skillet, add potato cubes and onions; cook for 5 minutes until tender and browned.", timer: 300 },
      { stepNumber: 3, text: "Add diced tomato and cook for 1 minute.", timer: 60 },
      { stepNumber: 4, text: "Pour beaten eggs directly into pan, reduce heat to medium-low, and push eggs gently until soft curds form.", timer: 120 },
      { stepNumber: 5, text: "Remove from heat, sprinkle cheddar cheese, and let melt for 30 seconds before plating.", timer: 30 }
    ]
  }
];

const DEFAULT_PANTRY = [
  "Chicken Breast", "Basmati Rice", "Tomato", "Onion", "Garlic", "Eggs", "Potato", "Spinach", "Cheese", "Olive Oil", "Butter"
];

// 2. APPLICATION STATE
let userPantry = JSON.parse(localStorage.getItem("pantrychef_pantry")) || [...DEFAULT_PANTRY];
let shoppingList = JSON.parse(localStorage.getItem("pantrychef_shopping")) || [
  { id: "shop_1", name: "Heavy Cream", checked: false },
  { id: "shop_2", name: "Parmesan Cheese", checked: false }
];
let favorites = JSON.parse(localStorage.getItem("pantrychef_favorites")) || ["rec_02"];
let activeCookingRecipe = null;
let currentCookingStepIndex = 0;
let timerInterval = null;
let timerSecondsRemaining = 0;
let speechEnabled = true;

// 3. RECIPE MATCHING ALGORITHM
function calculateRecipeMatches(pantryItems) {
  const pantrySet = new Set(pantryItems.map(i => i.toLowerCase().trim()));

  return RECIPES_DATA.map(recipe => {
    let haveCount = 0;
    const missing = [];
    const have = [];

    recipe.ingredients.forEach(ing => {
      const ingNameLower = ing.name.toLowerCase();
      // Fuzzy matching: e.g. "chicken" matches "chicken breast", "rice" matches "basmati rice"
      const matchFound = Array.from(pantrySet).some(p => ingNameLower.includes(p) || p.includes(ingNameLower));

      if (matchFound) {
        haveCount++;
        have.push(ing.name);
      } else {
        missing.push(ing.name);
      }
    });

    const matchPercent = Math.round((haveCount / recipe.ingredients.length) * 100);
    return {
      ...recipe,
      matchPercent,
      haveIngredients: have,
      missingIngredients: missing
    };
  }).sort((a, b) => b.matchPercent - a.matchPercent);
}

// 4. UI RENDERING FUNCTIONS
function renderPantryChips() {
  const container = document.getElementById("ingredientChipsGrid");
  if (!container) return;

  const popularSuggestions = [
    "Chicken Breast", "Basmati Rice", "Tomato", "Onion", "Garlic", "Eggs", "Potato",
    "Spinach", "Spaghetti", "Heavy Cream", "Parmesan Cheese", "Cheddar Cheese", "Butter",
    "Olive Oil", "Salmon Fillet", "Paneer", "Lemon", "Cumin Seeds"
  ];

  const uniqueAll = Array.from(new Set([...userPantry, ...popularSuggestions]));

  container.innerHTML = uniqueAll.map(ing => {
    const isSelected = userPantry.includes(ing);
    return `
      <div class="ingredient-toggle-chip ${isSelected ? 'selected' : ''}" onclick="togglePantryIngredient('${ing}')">
        <span class="chip-check">✓</span>
        <span>${ing}</span>
      </div>
    `;
  }).join("");

  document.getElementById("pantryCountBadge").textContent = userPantry.length;
}

function renderMatchingRecipes() {
  const container = document.getElementById("matchingRecipesGrid");
  if (!container) return;

  const matches = calculateRecipeMatches(userPantry);
  
  // Update Chef's Spotlight Hero with top match
  if (matches.length > 0) {
    const topMatch = matches[0];
    document.getElementById("spotlightTitle").textContent = topMatch.title;
    document.getElementById("spotlightDesc").textContent = topMatch.description;
    document.getElementById("spotlightImg").src = topMatch.imageUrl;
    document.getElementById("spotlightTime").textContent = `${topMatch.prepTimeMinutes + topMatch.cookTimeMinutes} min`;
    document.getElementById("spotlightCalories").textContent = `${topMatch.nutrition.calories} kcal`;
    document.getElementById("spotlightRating").textContent = `${topMatch.rating} (${topMatch.reviewsCount})`;
    document.getElementById("spotlightCuisine").textContent = topMatch.cuisine;
    document.getElementById("spotlightMatchBadge").textContent = `🔥 ${topMatch.matchPercent}% Match • Uses Kitchen Items`;
    
    document.getElementById("spotlightCookBtn").onclick = () => openRecipeDetail(topMatch.id);
  }

  container.innerHTML = matches.map(recipe => {
    const badgeClass = recipe.matchPercent >= 75 ? 'high' : 'medium';
    return `
      <div class="recipe-card" onclick="openRecipeDetail('${recipe.id}')">
        <div class="card-image-box">
          <img src="${recipe.imageUrl}" alt="${recipe.title}" class="card-image" loading="lazy">
          <div class="card-match-badge ${badgeClass}">
            <span>🔥</span>
            <span>${recipe.matchPercent}% Match</span>
          </div>
          <div class="card-cuisine-badge">${recipe.cuisine}</div>
        </div>
        <div class="card-body">
          <h3 class="card-title">${recipe.title}</h3>
          <p class="card-desc">${recipe.description}</p>
          
          <div class="ingredients-preview-row">
            ${recipe.haveIngredients.slice(0, 3).map(h => `<span class="ing-tag have">✓ ${h}</span>`).join('')}
            ${recipe.missingIngredients.slice(0, 2).map(m => `<span class="ing-tag need">+ ${m}</span>`).join('')}
          </div>

          <div class="card-stats">
            <span>⏱️ ${recipe.prepTimeMinutes + recipe.cookTimeMinutes}m</span>
            <span>🔥 ${recipe.nutrition.calories} kcal</span>
            <span>⭐ ${recipe.rating}</span>
          </div>
        </div>
      </div>
    `;
  }).join("");

  document.getElementById("matchStatsLabel").textContent = `Showing ${matches.length} curated recipes`;
}

function renderDiscoverRecipes(filterCuisine = "all", filterDiet = "all") {
  const container = document.getElementById("discoverRecipesGrid");
  if (!container) return;

  let list = [...RECIPES_DATA];
  if (filterCuisine !== "all") {
    list = list.filter(r => r.cuisine.toLowerCase() === filterCuisine.toLowerCase());
  }
  if (filterDiet !== "all") {
    list = list.filter(r => r.tags.some(t => t.toLowerCase() === filterDiet.toLowerCase()));
  }

  container.innerHTML = list.map(recipe => `
    <div class="recipe-card" onclick="openRecipeDetail('${recipe.id}')">
      <div class="card-image-box">
        <img src="${recipe.imageUrl}" alt="${recipe.title}" class="card-image" loading="lazy">
        <div class="card-cuisine-badge">${recipe.cuisine}</div>
      </div>
      <div class="card-body">
        <h3 class="card-title">${recipe.title}</h3>
        <p class="card-desc">${recipe.description}</p>
        <div class="card-stats">
          <span>⏱️ ${recipe.prepTimeMinutes + recipe.cookTimeMinutes}m</span>
          <span>🔥 ${recipe.nutrition.calories} kcal</span>
          <span>⭐ ${recipe.rating}</span>
        </div>
      </div>
    </div>
  `).join("");
}

function renderPantryCategories() {
  const container = document.getElementById("pantryCategoryColumns");
  if (!container) return;

  const categories = {
    "Produce & Herbs 🥬": ["Tomato", "Onion", "Garlic", "Potato", "Spinach", "Lemon"],
    "Meat & Seafood 🥩": ["Chicken Breast", "Salmon Fillet"],
    "Dairy & Eggs 🧀": ["Eggs", "Butter", "Cheese", "Cheddar Cheese", "Heavy Cream", "Parmesan Cheese", "Paneer"],
    "Pantry Staples & Grains 🌾": ["Basmati Rice", "Spaghetti", "Olive Oil", "Cumin Seeds"]
  };

  container.innerHTML = Object.entries(categories).map(([catTitle, defaultItems]) => {
    return `
      <div class="pantry-category-card">
        <div class="category-title-bar">${catTitle}</div>
        <div>
          ${defaultItems.map(item => {
            const hasItem = userPantry.includes(item);
            return `
              <div class="pantry-item-row">
                <span class="pantry-item-name" style="opacity: ${hasItem ? '1' : '0.45'};">
                  ${hasItem ? '🟢' : '⚪'} ${item}
                </span>
                <div class="pantry-item-qty">
                  <button class="qty-btn" onclick="togglePantryIngredient('${item}')">${hasItem ? '✕' : '+'}</button>
                </div>
              </div>
            `;
          }).join('')}
        </div>
      </div>
    `;
  }).join("");
}

function renderShoppingList() {
  const container = document.getElementById("shoppingListItems");
  if (!container) return;

  document.getElementById("shoppingCountBadge").textContent = shoppingList.filter(s => !s.checked).length;

  if (shoppingList.length === 0) {
    container.innerHTML = `<div style="text-align: center; color: var(--text-muted); padding: 2rem;">Your shopping list is clear!</div>`;
    return;
  }

  container.innerHTML = shoppingList.map(item => `
    <div class="shopping-item-row">
      <label class="shopping-item-left">
        <input type="checkbox" class="shopping-checkbox" ${item.checked ? 'checked' : ''} onchange="toggleShoppingCheck('${item.id}')">
        <span class="shopping-item-name ${item.checked ? 'completed' : ''}">${item.name}</span>
      </label>
      <button class="qty-btn" onclick="deleteShoppingItem('${item.id}')">🗑️</button>
    </div>
  `).join("");
}

function renderMealPlan() {
  const container = document.getElementById("mealPlanGrid");
  if (!container) return;

  const days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
  const sampleMeals = [
    { b: "Classic Fluffy French Omelette", l: "Chicken Tomato Rice Skillet", d: "Creamy Garlic Chicken" },
    { b: "Loaded Breakfast Scramble", l: "Quick Garlic Butter Spaghetti", d: "Honey Garlic Glazed Salmon" },
    { b: "Classic Fluffy French Omelette", l: "Aloo Jeera (Spiced Cumin)", d: "Paneer Butter Masala" },
    { b: "Loaded Breakfast Scramble", l: "Chicken Tomato Rice Skillet", d: "Creamy Garlic Chicken" },
    { b: "Classic Fluffy French Omelette", l: "Quick Garlic Butter Spaghetti", d: "Honey Garlic Glazed Salmon" },
    { b: "Loaded Breakfast Scramble", l: "Paneer Butter Masala", d: "Chicken Tomato Rice Skillet" },
    { b: "Loaded Breakfast Scramble", l: "Aloo Jeera (Spiced Cumin)", d: "Creamy Garlic Chicken" }
  ];

  container.innerHTML = days.map((day, idx) => {
    const meal = sampleMeals[idx];
    return `
      <div class="day-card">
        <div class="day-header">${day}</div>
        <div class="meal-slot">
          <div class="slot-label">Breakfast</div>
          <div class="slot-recipe-name">${meal.b}</div>
        </div>
        <div class="meal-slot">
          <div class="slot-label">Lunch</div>
          <div class="slot-recipe-name">${meal.l}</div>
        </div>
        <div class="meal-slot">
          <div class="slot-label">Dinner</div>
          <div class="slot-recipe-name">${meal.d}</div>
        </div>
      </div>
    `;
  }).join("");
}

// 5. INTERACTION HANDLERS
window.togglePantryIngredient = function(ingName) {
  if (userPantry.includes(ingName)) {
    userPantry = userPantry.filter(i => i !== ingName);
  } else {
    userPantry.push(ingName);
  }
  localStorage.setItem("pantrychef_pantry", JSON.stringify(userPantry));
  renderPantryChips();
  renderMatchingRecipes();
  renderPantryCategories();
};

window.openRecipeDetail = function(recipeId) {
  const recipe = RECIPES_DATA.find(r => r.id === recipeId);
  if (!recipe) return;

  const modal = document.getElementById("recipeDetailModal");
  const modalTitle = document.getElementById("detailModalTitle");
  const modalBody = document.getElementById("detailModalBody");

  modalTitle.textContent = recipe.title;

  const haveSet = new Set(userPantry.map(i => i.toLowerCase()));

  modalBody.innerHTML = `
    <div style="margin-bottom: 1.5rem; border-radius: var(--radius-lg); overflow: hidden; height: 260px;">
      <img src="${recipe.imageUrl}" alt="${recipe.title}" style="width: 100%; height: 100%; object-fit: cover;">
    </div>
    
    <p style="color: var(--text-secondary); margin-bottom: 1.5rem; font-size: 1.05rem;">${recipe.description}</p>
    
    <div class="recipe-meta-row" style="margin-bottom: 1.5rem;">
      <span class="meta-chip">⏱️ Prep: ${recipe.prepTimeMinutes}m</span>
      <span class="meta-chip">🍳 Cook: ${recipe.cookTimeMinutes}m</span>
      <span class="meta-chip">🔥 ${recipe.nutrition.calories} kcal</span>
      <span class="meta-chip highlight">⭐ ${recipe.rating} (${recipe.reviewsCount} reviews)</span>
    </div>

    <div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 1rem; margin-bottom: 1.75rem; background: var(--bg-surface); padding: 1rem; border-radius: var(--radius-md); text-align: center;">
      <div>
        <div style="font-size: 0.75rem; color: var(--text-muted); text-transform: uppercase;">Protein</div>
        <div style="font-size: 1.2rem; font-weight: 700; color: #f59e0b;">${recipe.nutrition.protein}g</div>
      </div>
      <div>
        <div style="font-size: 0.75rem; color: var(--text-muted); text-transform: uppercase;">Carbs</div>
        <div style="font-size: 1.2rem; font-weight: 700; color: #3b82f6;">${recipe.nutrition.carbs}g</div>
      </div>
      <div>
        <div style="font-size: 0.75rem; color: var(--text-muted); text-transform: uppercase;">Fat</div>
        <div style="font-size: 1.2rem; font-weight: 700; color: #10b981;">${recipe.nutrition.fat}g</div>
      </div>
    </div>

    <h4 style="font-family: var(--font-heading); margin-bottom: 0.75rem; font-size: 1.15rem;">Ingredients (${recipe.servings} Servings)</h4>
    <div style="display: flex; flex-direction: column; gap: 0.5rem; margin-bottom: 2rem;">
      ${recipe.ingredients.map(ing => {
        const inPantry = Array.from(haveSet).some(p => ing.name.toLowerCase().includes(p) || p.includes(ing.name.toLowerCase()));
        return `
          <div style="display: flex; justify-content: space-between; padding: 0.5rem 0.75rem; background: var(--bg-surface); border-radius: var(--radius-sm);">
            <span>${inPantry ? '🟢' : '⚪'} ${ing.name}</span>
            <span style="color: var(--text-muted); font-weight: 600;">${ing.amount} ${ing.unit}</span>
          </div>
        `;
      }).join('')}
    </div>

    <div style="display: flex; gap: 1rem; justify-content: flex-end;">
      <button class="btn-secondary" onclick="addMissingToShopping('${recipe.id}')">🛒 Add Missing to Shopping</button>
      <button class="btn-primary-large" onclick="startCookingMode('${recipe.id}')">👨‍🍳 Start Cooking Mode →</button>
    </div>
  `;

  modal.classList.add("open");
};

window.addMissingToShopping = function(recipeId) {
  const recipe = RECIPES_DATA.find(r => r.id === recipeId);
  if (!recipe) return;

  const haveSet = new Set(userPantry.map(i => i.toLowerCase()));
  let addedCount = 0;

  recipe.ingredients.forEach(ing => {
    const inPantry = Array.from(haveSet).some(p => ing.name.toLowerCase().includes(p) || p.includes(ing.name.toLowerCase()));
    if (!inPantry && !shoppingList.some(s => s.name.toLowerCase() === ing.name.toLowerCase())) {
      shoppingList.push({ id: `shop_${Date.now()}_${Math.random()}`, name: ing.name, checked: false });
      addedCount++;
    }
  });

  localStorage.setItem("pantrychef_shopping", JSON.stringify(shoppingList));
  renderShoppingList();
  alert(addedCount > 0 ? `Added ${addedCount} missing ingredient(s) to Shopping List!` : `All ingredients are already in your list or kitchen!`);
};

// 6. INTERACTIVE COOKING MODE
window.startCookingMode = function(recipeId) {
  const recipe = RECIPES_DATA.find(r => r.id === recipeId);
  if (!recipe) return;

  activeCookingRecipe = recipe;
  currentCookingStepIndex = 0;

  document.getElementById("recipeDetailModal").classList.remove("open");
  document.getElementById("cookingModeModal").classList.add("open");

  updateCookingStepView();
};

function updateCookingStepView() {
  if (!activeCookingRecipe) return;

  const step = activeCookingRecipe.steps[currentCookingStepIndex];
  const totalSteps = activeCookingRecipe.steps.length;

  document.getElementById("cookingRecipeName").textContent = activeCookingRecipe.title;
  document.getElementById("cookingStepCounter").textContent = `STEP ${currentCookingStepIndex + 1} OF ${totalSteps}`;
  document.getElementById("cookingProgressBar").style.width = `${((currentCookingStepIndex + 1) / totalSteps) * 100}%`;
  document.getElementById("cookingStepTitle").textContent = `Step ${currentCookingStepIndex + 1}`;
  document.getElementById("cookingStepInstruction").textContent = step.text;

  // Render current step ingredient checklist
  const checklist = document.getElementById("cookingStepChecklist");
  checklist.innerHTML = activeCookingRecipe.ingredients.slice(0, 4).map(ing => `
    <li class="checklist-item">
      <span>✓ ${ing.name}</span>
      <span>${ing.amount} ${ing.unit}</span>
    </li>
  `).join("");

  // Setup timer if step has one
  const timerBox = document.getElementById("cookingTimerContainer");
  clearInterval(timerInterval);
  if (step.timer && step.timer > 0) {
    timerBox.style.display = "flex";
    timerSecondsRemaining = step.timer;
    renderTimerDisplay();
  } else {
    timerBox.style.display = "none";
  }

  // Speak aloud if speech is enabled
  if (speechEnabled && 'speechSynthesis' in window) {
    window.speechSynthesis.cancel();
    const utterance = new SpeechSynthesisUtterance(`Step ${currentCookingStepIndex + 1}: ${step.text}`);
    utterance.rate = 1.0;
    window.speechSynthesis.speak(utterance);
  }

  document.getElementById("cookingPrevBtn").style.visibility = currentCookingStepIndex > 0 ? "visible" : "hidden";
  document.getElementById("cookingNextBtn").textContent = (currentCookingStepIndex === totalSteps - 1) ? "Finish Cooking 🎉" : "Next Step →";
}

function renderTimerDisplay() {
  const mins = Math.floor(timerSecondsRemaining / 60).toString().padStart(2, '0');
  const secs = (timerSecondsRemaining % 60).toString().padStart(2, '0');
  document.getElementById("cookingTimerDisplay").textContent = `${mins}:${secs}`;
}

// 7. SHOPPING LIST ACTIONS
window.toggleShoppingCheck = function(id) {
  shoppingList = shoppingList.map(item => item.id === id ? { ...item, checked: !item.checked } : item);
  localStorage.setItem("pantrychef_shopping", JSON.stringify(shoppingList));
  renderShoppingList();
};

window.deleteShoppingItem = function(id) {
  shoppingList = shoppingList.filter(item => item.id !== id);
  localStorage.setItem("pantrychef_shopping", JSON.stringify(shoppingList));
  renderShoppingList();
};

// 0. OPENING SPLASH SCREEN LOGIC (Matches Android SplashScreen.kt)
function initOpeningSplashScreen() {
  const splashEl = document.getElementById("openingSplashScreen");
  const percentEl = document.getElementById("splashPercent");
  const fillEl = document.getElementById("splashProgressFill");
  if (!splashEl || !percentEl || !fillEl) return;

  splashEl.classList.remove("hidden");
  let progress = 0;
  let hasSkipped = false;
  const duration = 2500; // 2.5 seconds matching Android SplashScreen.kt
  const intervalTime = 25;
  const increment = 100 / (duration / intervalTime);

  const timer = setInterval(() => {
    if (hasSkipped) {
      clearInterval(timer);
      return;
    }
    progress += increment;
    if (progress >= 100) {
      progress = 100;
      clearInterval(timer);
      percentEl.textContent = "100%";
      fillEl.style.width = "100%";
      setTimeout(() => {
        dismissSplash();
      }, 150);
    } else {
      percentEl.textContent = `${Math.floor(progress)}%`;
      fillEl.style.width = `${progress}%`;
    }
  }, intervalTime);

  function dismissSplash() {
    hasSkipped = true;
    clearInterval(timer);
    splashEl.classList.add("hidden");
  }

  splashEl.onclick = () => dismissSplash();

  document.getElementById("replaySplashBtn")?.addEventListener("click", () => {
    initOpeningSplashScreen();
  });
}

// 8. EVENT LISTENERS INITIALIZATION
document.addEventListener("DOMContentLoaded", () => {
  initOpeningSplashScreen();
  renderPantryChips();
  renderMatchingRecipes();
  renderDiscoverRecipes();
  renderPantryCategories();
  renderShoppingList();
  renderMealPlan();

  // Tab Navigation Switching
  document.querySelectorAll(".tab-btn").forEach(btn => {
    btn.addEventListener("click", () => {
      document.querySelectorAll(".tab-btn").forEach(b => b.classList.remove("active"));
      document.querySelectorAll(".tab-view").forEach(v => v.classList.remove("active"));

      btn.classList.add("active");
      const targetId = `${btn.dataset.tab}View`;
      const targetView = document.getElementById(targetId);
      if (targetView) targetView.classList.add("active");
    });
  });

  // Modal Closing Buttons
  document.querySelectorAll("[data-close]").forEach(btn => {
    btn.addEventListener("click", () => {
      const modalId = btn.dataset.close;
      document.getElementById(modalId)?.classList.remove("open");
      clearInterval(timerInterval);
      if ('speechSynthesis' in window) window.speechSynthesis.cancel();
    });
  });

  // Close modals when clicking backdrop
  document.querySelectorAll(".modal-overlay").forEach(modal => {
    modal.addEventListener("click", (e) => {
      if (e.target === modal) {
        modal.classList.remove("open");
        clearInterval(timerInterval);
        if ('speechSynthesis' in window) window.speechSynthesis.cancel();
      }
    });
  });

  // Cooking Navigation Next/Prev
  document.getElementById("cookingNextBtn")?.addEventListener("click", () => {
    if (!activeCookingRecipe) return;
    if (currentCookingStepIndex < activeCookingRecipe.steps.length - 1) {
      currentCookingStepIndex++;
      updateCookingStepView();
    } else {
      alert("🎉 Congratulations, Chef! Your dish is complete and ready to serve!");
      document.getElementById("cookingModeModal").classList.remove("open");
    }
  });

  document.getElementById("cookingPrevBtn")?.addEventListener("click", () => {
    if (currentCookingStepIndex > 0) {
      currentCookingStepIndex--;
      updateCookingStepView();
    }
  });

  // Timer controls
  document.getElementById("timerStartBtn")?.addEventListener("click", () => {
    clearInterval(timerInterval);
    timerInterval = setInterval(() => {
      if (timerSecondsRemaining > 0) {
        timerSecondsRemaining--;
        renderTimerDisplay();
      } else {
        clearInterval(timerInterval);
        alert("⏰ Step timer finished! Check your pan.");
      }
    }, 1000);
  });

  document.getElementById("timerResetBtn")?.addEventListener("click", () => {
    clearInterval(timerInterval);
    if (activeCookingRecipe) {
      timerSecondsRemaining = activeCookingRecipe.steps[currentCookingStepIndex].timer || 0;
      renderTimerDisplay();
    }
  });

  // Speech toggle
  document.getElementById("speechToggleBtn")?.addEventListener("click", () => {
    speechEnabled = !speechEnabled;
    document.getElementById("speechToggleBtn").textContent = speechEnabled ? "🔊" : "🔇";
  });

  // Add custom ingredient input
  document.getElementById("addQuickIngBtn")?.addEventListener("click", () => {
    const input = document.getElementById("quickIngredientInput");
    const val = input.value.trim();
    if (val) {
      togglePantryIngredient(val);
      input.value = "";
    }
  });

  // Quick prompt chip filters in Discover view
  document.querySelectorAll("#cuisineFilters .quick-prompt-chip").forEach(btn => {
    btn.addEventListener("click", () => {
      document.querySelectorAll("#cuisineFilters .quick-prompt-chip").forEach(b => b.classList.remove("active"));
      btn.classList.add("active");
      renderDiscoverRecipes(btn.dataset.cuisine, "all");
    });
  });

  document.querySelectorAll("#dietFilters .quick-prompt-chip").forEach(btn => {
    btn.addEventListener("click", () => {
      document.querySelectorAll("#dietFilters .quick-prompt-chip").forEach(b => b.classList.remove("active"));
      btn.classList.add("active");
      renderDiscoverRecipes("all", btn.dataset.diet);
    });
  });

  // Shopping list add item
  document.getElementById("addShoppingItemBtn")?.addEventListener("click", () => {
    const input = document.getElementById("newShoppingItemInput");
    const val = input.value.trim();
    if (val) {
      shoppingList.push({ id: `shop_${Date.now()}`, name: val, checked: false });
      localStorage.setItem("pantrychef_shopping", JSON.stringify(shoppingList));
      input.value = "";
      renderShoppingList();
    }
  });

  document.getElementById("clearCompletedShoppingBtn")?.addEventListener("click", () => {
    shoppingList = shoppingList.filter(item => !item.checked);
    localStorage.setItem("pantrychef_shopping", JSON.stringify(shoppingList));
    renderShoppingList();
  });

  document.getElementById("moveToPantryBtn")?.addEventListener("click", () => {
    const checkedItems = shoppingList.filter(item => item.checked).map(item => item.name);
    if (checkedItems.length === 0) {
      alert("Check the items you have bought first!");
      return;
    }
    checkedItems.forEach(item => {
      if (!userPantry.includes(item)) userPantry.push(item);
    });
    shoppingList = shoppingList.filter(item => !item.checked);
    localStorage.setItem("pantrychef_pantry", JSON.stringify(userPantry));
    localStorage.setItem("pantrychef_shopping", JSON.stringify(shoppingList));
    renderPantryChips();
    renderMatchingRecipes();
    renderShoppingList();
    alert(`Moved ${checkedItems.length} item(s) to your Kitchen Pantry!`);
  });

  // AI Assistant Modal Trigger
  document.getElementById("openAiAssistantBtn")?.addEventListener("click", () => {
    document.getElementById("aiAssistantModal").classList.add("open");
  });

  // AI Chat form
  document.getElementById("aiChatForm")?.addEventListener("submit", (e) => {
    e.preventDefault();
    const input = document.getElementById("aiChatInput");
    const msg = input.value.trim();
    if (!msg) return;

    appendAiMessage("user", msg);
    input.value = "";

    // Simulated Chef Gemini response with smart culinary advice
    setTimeout(() => {
      const response = generateAiChefResponse(msg);
      appendAiMessage("bot", response);
    }, 600);
  });

  // AI Quick prompts
  document.querySelectorAll(".quick-prompts-row .quick-prompt-chip").forEach(chip => {
    chip.addEventListener("click", () => {
      const prompt = chip.dataset.prompt;
      appendAiMessage("user", prompt);
      setTimeout(() => {
        const response = generateAiChefResponse(prompt);
        appendAiMessage("bot", response);
      }, 500);
    });
  });

  // Global search input
  document.getElementById("globalSearchInput")?.addEventListener("input", (e) => {
    const q = e.target.value.toLowerCase().trim();
    if (!q) {
      renderMatchingRecipes();
      return;
    }

    const filtered = RECIPES_DATA.filter(r => 
      r.title.toLowerCase().includes(q) || 
      r.cuisine.toLowerCase().includes(q) ||
      r.ingredients.some(i => i.name.toLowerCase().includes(q))
    );

    const container = document.getElementById("matchingRecipesGrid");
    container.innerHTML = filtered.map(recipe => `
      <div class="recipe-card" onclick="openRecipeDetail('${recipe.id}')">
        <div class="card-image-box">
          <img src="${recipe.imageUrl}" alt="${recipe.title}" class="card-image">
          <div class="card-cuisine-badge">${recipe.cuisine}</div>
        </div>
        <div class="card-body">
          <h3 class="card-title">${recipe.title}</h3>
          <p class="card-desc">${recipe.description}</p>
          <div class="card-stats">
            <span>⏱️ ${recipe.prepTimeMinutes + recipe.cookTimeMinutes}m</span>
            <span>🔥 ${recipe.nutrition.calories} kcal</span>
            <span>⭐ ${recipe.rating}</span>
          </div>
        </div>
      </div>
    `).join("");
  });
});

function appendAiMessage(sender, text) {
  const chatScroll = document.getElementById("aiChatMessages");
  const msgEl = document.createElement("div");
  msgEl.className = `ai-message ${sender}`;
  msgEl.innerHTML = `
    <div class="message-avatar">${sender === 'bot' ? '👨‍🍳' : '👤'}</div>
    <div class="message-bubble">${text}</div>
  `;
  chatScroll.appendChild(msgEl);
  chatScroll.scrollTop = chatScroll.scrollHeight;
}

function generateAiChefResponse(query) {
  const q = query.toLowerCase();
  if (q.includes("chicken") && q.includes("rice")) {
    return "Great combination! You have everything for our **Chicken Tomato Rice Skillet** (rec_02) or **Creamy Garlic Chicken** over basmati rice. Sear the chicken with olive oil and garlic first to build deep fond on the pan bottom!";
  }
  if (q.includes("substitute") || q.includes("cream")) {
    return "For **Heavy Cream**, you can substitute Greek yogurt with a splash of milk (1:1 ratio for a rich, tangy sauce), or whole milk blended with melted butter (3:1 ratio). Both work wonderfully in skillet pastas!";
  }
  if (q.includes("breakfast") || q.includes("protein")) {
    return "For a quick high-protein breakfast, prepare the **Loaded Breakfast Scramble** (20g protein in 10 mins) or our **Classic French Omelette** folded with cheddar cheese (22g protein). Whisk vigorously with a fork for 60s for airy texture!";
  }
  if (q.includes("low carb") || q.includes("keto")) {
    return "The **Creamy Garlic Chicken with Wilted Spinach** has only 6g carbs with 46g protein and 480 kcal. You can also pair garlic-seared shrimp with a crisp zucchini sauté for under 5g carbs!";
  }
  return `Chef tip: Based on your current pantry (${userPantry.slice(0, 4).join(', ')}), you can cook a hearty skillet meal in under 25 minutes! Try clicking on any recipe in your Kitchen tab to start guided step-by-step cooking with live timers.`;
}
