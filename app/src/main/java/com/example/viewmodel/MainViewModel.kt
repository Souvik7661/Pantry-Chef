package com.example.viewmodel

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Immutable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.engine.RecipeMatchingEngine
import com.example.data.local.AppDatabase
import com.example.data.local.CookingHistoryEntity
import com.example.data.local.MealPlanEntity
import com.example.data.local.PantryEntity
import com.example.data.local.ShoppingItemEntity
import com.example.data.local.UserPreferencesEntity
import com.example.data.model.MealType
import com.example.data.model.Recipe
import com.example.data.model.RecipeIngredient
import com.example.data.model.RecipeMatchResult
import com.example.data.remote.GeminiCookingService
import com.example.data.repository.MealPlanRepository
import com.example.data.repository.PantryRepository
import com.example.data.repository.RecipeRepository
import com.example.data.repository.ShoppingRepository
import com.example.data.repository.UserPreferencesRepository
import com.example.data.sample.SeedData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale

@Immutable
data class ActiveTimer(
    val id: String,
    val title: String,
    val totalSeconds: Int,
    val remainingSeconds: Int,
    val isRunning: Boolean = true
)

enum class RecipeSortOption(val displayName: String) {
    BEST_MATCH("Best Match %"),
    LEAST_MISSING("Fewest Missing"),
    SHORTEST_TIME("Quickest Cook Time"),
    HIGHEST_RATED("Highest Rated")
}

@Immutable
data class FilterState(
    val cuisine: String = "All",
    val mealType: MealType? = null,
    val maxCookingTime: Int? = null,
    val difficulty: String = "All",
    val dietaryFilters: Set<String> = emptySet(),
    val sortOption: RecipeSortOption = RecipeSortOption.BEST_MATCH
)

class MainViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {

    private val database = AppDatabase.getInstance(application)
    val recipeRepository = RecipeRepository(database)
    val pantryRepository = PantryRepository(database)
    val mealPlanRepository = MealPlanRepository(database, recipeRepository)
    val shoppingRepository = ShoppingRepository(database, database.pantryDao())
    val userPreferencesRepository = UserPreferencesRepository(database)

    // User preferences & Onboarding
    val userPreferences: StateFlow<UserPreferencesEntity?> =
        userPreferencesRepository.preferences
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    // Pantry inventory
    val activePantryItems: StateFlow<List<PantryEntity>> =
        pantryRepository.activePantryItems
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val expiringPantryItems: StateFlow<List<PantryEntity>> =
        pantryRepository.expiringSoonItems
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Favorites
    val favoriteIds: StateFlow<List<String>> =
        recipeRepository.getFavoriteIds()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Meal plan
    val weeklyMealPlan: StateFlow<List<MealPlanEntity>> =
        mealPlanRepository.weeklyPlan
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Shopping items
    val shoppingItems: StateFlow<List<ShoppingItemEntity>> =
        shoppingRepository.items
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Cooking history
    val cookingHistory: StateFlow<List<CookingHistoryEntity>> =
        recipeRepository.getCookingHistory()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Current Search & Matching
    private val _selectedIngredients = MutableStateFlow<List<String>>(
        listOf("Chicken", "Tomato", "Onion", "Garlic", "Rice")
    )
    val selectedIngredients: StateFlow<List<String>> = _selectedIngredients.asStateFlow()

    private val _filterState = MutableStateFlow(FilterState())
    val filterState: StateFlow<FilterState> = _filterState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Calculated recipe match results
    val recipeMatches: StateFlow<List<RecipeMatchResult>> = combine(
        _selectedIngredients,
        expiringPantryItems,
        _filterState,
        _searchQuery,
        userPreferences
    ) { ingredients, expiring, filters, query, prefs ->
        val userAllergies = prefs?.allergiesCsv?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
        val userDiet = prefs?.dietaryPreferencesCsv?.split(",")?.filter { it.isNotBlank() } ?: emptyList()

        val allRecipes = recipeRepository.getAllRecipes()

        // Base matching and ranking
        var results = RecipeMatchingEngine.rankRecipes(
            recipes = allRecipes,
            userIngredients = ingredients,
            expiringItems = expiring,
            userAllergies = userAllergies,
            dietaryPreferences = userDiet
        )

        // Apply search query
        if (query.isNotBlank()) {
            val q = query.trim().lowercase()
            results = results.filter {
                it.recipe.title.lowercase().contains(q) ||
                        it.recipe.cuisine.lowercase().contains(q) ||
                        it.recipe.tags.any { tag -> tag.lowercase().contains(q) } ||
                        it.recipe.ingredients.any { ing -> ing.name.lowercase().contains(q) }
            }
        }

        // Apply filters
        if (filters.cuisine != "All") {
            results = results.filter { it.recipe.cuisine.equals(filters.cuisine, ignoreCase = true) }
        }
        if (filters.mealType != null) {
            results = results.filter { it.recipe.mealTypes.contains(filters.mealType) }
        }
        if (filters.maxCookingTime != null) {
            results = results.filter { it.recipe.totalTimeMinutes <= filters.maxCookingTime }
        }
        if (filters.difficulty != "All") {
            results = results.filter { it.recipe.difficulty.displayName.equals(filters.difficulty, ignoreCase = true) }
        }
        if (filters.dietaryFilters.isNotEmpty()) {
            results = results.filter { r ->
                filters.dietaryFilters.all { d ->
                    when (d.lowercase()) {
                        "vegetarian" -> r.recipe.isVegetarian
                        "vegan" -> r.recipe.isVegan
                        "high protein" -> r.recipe.isHighProtein
                        "low carb" -> r.recipe.isLowCarb
                        "gluten free" -> r.recipe.isGlutenFree
                        "keto" -> r.recipe.isKeto
                        else -> true
                    }
                }
            }
        }

        // Apply sorting
        when (filters.sortOption) {
            RecipeSortOption.BEST_MATCH -> results.sortedByDescending { it.matchPercentage }
            RecipeSortOption.LEAST_MISSING -> results.sortedBy { it.missingIngredients.size }
            RecipeSortOption.SHORTEST_TIME -> results.sortedBy { it.recipe.totalTimeMinutes }
            RecipeSortOption.HIGHEST_RATED -> results.sortedByDescending { it.recipe.rating }
        }
    }.flowOn(Dispatchers.Default)
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Signature Feature: Kitchen Rescue
    val kitchenRescueRecommendation: StateFlow<RecipeMatchResult?> = combine(
        activePantryItems,
        userPreferences
    ) { pantry, prefs ->
        val userAllergies = prefs?.allergiesCsv?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
        val userDiet = prefs?.dietaryPreferencesCsv?.split(",")?.filter { it.isNotBlank() } ?: emptyList()

        RecipeMatchingEngine.kitchenRescue(
            recipes = recipeRepository.getAllRecipes(),
            pantryItems = pantry,
            userAllergies = userAllergies,
            dietaryPreferences = userDiet
        )
    }.flowOn(Dispatchers.Default)
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // Active Timers System
    private val _activeTimers = MutableStateFlow<List<ActiveTimer>>(emptyList())
    val activeTimers: StateFlow<List<ActiveTimer>> = _activeTimers.asStateFlow()
    private var timerLoopJob: Job? = null

    // Text To Speech
    private var tts: TextToSpeech? = null
    private val _ttsReady = MutableStateFlow(false)
    val ttsReady: StateFlow<Boolean> = _ttsReady.asStateFlow()

    // AI Generation State
    private val _isGeneratingAi = MutableStateFlow(false)
    val isGeneratingAi: StateFlow<Boolean> = _isGeneratingAi.asStateFlow()
    val isGeneratingRecipe: StateFlow<Boolean> get() = isGeneratingAi

    private val _aiGeneratedRecipe = MutableStateFlow<Recipe?>(null)
    val aiGeneratedRecipe: StateFlow<Recipe?> = _aiGeneratedRecipe.asStateFlow()

    init {
        // Initialize TTS
        try {
            tts = TextToSpeech(application, this)
        } catch (e: Exception) {
            // TTS unavailable
        }

        // Preseed initial pantry if empty
        viewModelScope.launch {
            pantryRepository.seedDefaultPantryIfEmpty()
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.US
            _ttsReady.value = true
        }
    }

    fun speakInstruction(text: String) {
        if (_ttsReady.value) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "step_tts")
        }
    }

    fun stopSpeaking() {
        tts?.stop()
    }

    override fun onCleared() {
        super.onCleared()
        tts?.stop()
        tts?.shutdown()
        timerLoopJob?.cancel()
    }

    // Ingredient Management
    fun addIngredient(name: String) {
        val trimmed = name.trim()
        if (trimmed.isNotBlank() && !_selectedIngredients.value.any { it.equals(trimmed, ignoreCase = true) }) {
            _selectedIngredients.value = _selectedIngredients.value + trimmed
        }
    }

    fun removeIngredient(name: String) {
        _selectedIngredients.value = _selectedIngredients.value.filterNot { it.equals(name, ignoreCase = true) }
    }

    fun clearIngredients() {
        _selectedIngredients.value = emptyList()
    }

    fun useAllPantryIngredients() {
        val pantryNames = activePantryItems.value.map { it.name }
        if (pantryNames.isNotEmpty()) {
            _selectedIngredients.value = pantryNames
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun updateFilter(update: (FilterState) -> FilterState) {
        _filterState.value = update(_filterState.value)
    }

    fun resetFilters() {
        _filterState.value = FilterState()
    }

    // Camera Ingredient Detection
    suspend fun analyzePhotoForIngredients(bitmap: Bitmap): List<String> {
        return GeminiCookingService.detectIngredientsFromImage(bitmap)
    }

    // AI Generation
    fun generateAiRecipe(modification: String? = null) {
        generateAiRecipe(_selectedIngredients.value, modification)
    }

    fun generateAiRecipe(ingredients: List<String>, modification: String? = null) {
        viewModelScope.launch {
            _isGeneratingAi.value = true
            _aiGeneratedRecipe.value = null
            try {
                val prefs = userPreferences.value
                val userAllergies = prefs?.allergiesCsv?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
                val userDiet = prefs?.dietaryPreferencesCsv?.split(",")?.filter { it.isNotBlank() } ?: emptyList()

                val recipe = GeminiCookingService.generateRecipeWithAI(
                    userIngredients = ingredients,
                    dietaryPreferences = userDiet,
                    allergies = userAllergies,
                    customInstruction = modification
                )
                _aiGeneratedRecipe.value = recipe
            } catch (e: Exception) {
                // Handled gracefully in service
            } finally {
                _isGeneratingAi.value = false
            }
        }
    }

    // Timers System
    fun startTimer(title: String, seconds: Int) {
        val timerId = "timer_${System.currentTimeMillis()}"
        val newTimer = ActiveTimer(
            id = timerId,
            title = title,
            totalSeconds = seconds,
            remainingSeconds = seconds,
            isRunning = true
        )
        _activeTimers.value = _activeTimers.value + newTimer
        ensureTimerLoop()
    }

    fun pauseTimer(id: String) {
        _activeTimers.value = _activeTimers.value.map {
            if (it.id == id) it.copy(isRunning = !it.isRunning) else it
        }
    }

    fun cancelTimer(id: String) {
        _activeTimers.value = _activeTimers.value.filterNot { it.id == id }
    }

    private fun ensureTimerLoop() {
        if (timerLoopJob?.isActive == true) return
        timerLoopJob = viewModelScope.launch {
            while (_activeTimers.value.isNotEmpty()) {
                delay(1000)
                var hasFinishedTimer = false
                _activeTimers.value = _activeTimers.value.mapNotNull { timer ->
                    if (!timer.isRunning) {
                        timer
                    } else if (timer.remainingSeconds > 1) {
                        timer.copy(remainingSeconds = timer.remainingSeconds - 1)
                    } else {
                        hasFinishedTimer = true
                        null // Finished
                    }
                }
                if (hasFinishedTimer) {
                    triggerTimerAlert()
                }
            }
        }
    }

    private fun triggerTimerAlert() {
        try {
            val context = getApplication<Application>()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vibratorManager?.defaultVibrator?.vibrate(
                    VibrationEffect.createWaveform(longArrayOf(0, 300, 200, 300), -1)
                )
            } else {
                @Suppress("DEPRECATION")
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
                @Suppress("DEPRECATION")
                vibrator?.vibrate(longArrayOf(0, 300, 200, 300), -1)
            }
            speakInstruction("Cooking timer complete!")
        } catch (e: Exception) {
            // Ignore vibration error on emulators
        }
    }

    // Onboarding & Preferences
    fun completeOnboarding(
        name: String,
        dietary: List<String>,
        allergies: List<String>,
        skill: String,
        time: Int,
        starterPantrySelection: List<String>
    ) {
        viewModelScope.launch {
            userPreferencesRepository.savePreferences(
                userName = name,
                dietaryPreferences = dietary,
                allergies = allergies,
                cookingSkill = skill,
                preferredTimeMinutes = time,
                onboarded = true
            )

            // Add chosen starter pantry items if any
            for (p in starterPantrySelection) {
                pantryRepository.addItem(
                    name = p,
                    quantity = 2f,
                    unit = "pcs",
                    category = "Pantry Staples",
                    daysRemaining = 10
                )
            }
        }
    }

    // Toggle favorite
    fun toggleFavorite(recipeId: String) {
        viewModelScope.launch {
            val isFav = favoriteIds.value.contains(recipeId)
            recipeRepository.toggleFavorite(recipeId, isFav)
        }
    }

    // Add to shopping
    fun addMissingToShopping(recipe: Recipe, missingIngredients: List<RecipeIngredient>) {
        viewModelScope.launch {
            shoppingRepository.addMissingIngredientsWithPantryIntelligence(
                missing = missingIngredients,
                recipeTitle = recipe.title
            )
        }
    }
}
