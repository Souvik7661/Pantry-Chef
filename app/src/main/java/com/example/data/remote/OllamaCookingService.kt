package com.example.data.remote

import com.example.data.model.DifficultyLevel
import com.example.data.model.MealType
import com.example.data.model.NutritionInfo
import com.example.data.model.Recipe
import com.example.data.model.RecipeIngredient
import com.example.data.model.RecipeStep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object OllamaCookingService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(12, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    // Default host for Android Emulator to connect to host machine Ollama; customizable for real devices
    @Volatile
    var hostUrl: String = "http://10.0.2.2:11434"

    @Volatile
    var preferredModel: String = "llama3.2:latest"

    /**
     * Checks if Ollama server is reachable and discovers installed models.
     */
    suspend fun checkConnectionAndGetModels(): List<String> = withContext(Dispatchers.IO) {
        val urlsToTry = listOf(hostUrl, "http://127.0.0.1:11434", "http://localhost:11434")
        for (url in urlsToTry) {
            try {
                val request = Request.Builder()
                    .url("$url/api/tags")
                    .get()
                    .build()
                val response = client.newCall(request).execute()
                val body = response.body?.string()
                if (response.isSuccessful && !body.isNullOrEmpty()) {
                    hostUrl = url // Cache working host
                    val root = JSONObject(body)
                    val modelsArray = root.optJSONArray("models") ?: JSONArray()
                    val modelNames = mutableListOf<String>()
                    for (i in 0 until modelsArray.length()) {
                        val m = modelsArray.getJSONObject(i)
                        modelNames.add(m.optString("name"))
                    }
                    if (modelNames.isNotEmpty()) {
                        // Pick first conversational model if available
                        val matched = modelNames.firstOrNull {
                            it.contains("llama", ignoreCase = true) ||
                            it.contains("mistral", ignoreCase = true) ||
                            it.contains("gemma", ignoreCase = true)
                        } ?: modelNames.first()
                        preferredModel = matched
                    }
                    return@withContext modelNames
                }
            } catch (_: Exception) {
                // Try next
            }
        }
        emptyList()
    }

    /**
     * Queries Ollama for culinary advice, ingredient substitutions, or recipes outside the catalog.
     */
    suspend fun askMrFoodie(
        question: String,
        userName: String = "Chef",
        history: List<Pair<String, String>> = emptyList()
    ): String = withContext(Dispatchers.IO) {
        val systemPrompt = "You are Mr. Foodie, a friendly, enthusiastic, and master chef assistant inside the PantryChef app. " +
                "You address the user warmly as '$userName'. Answer their culinary questions, give cooking advice, suggest ingredient substitutions, " +
                "or explain recipes with exact measurements and step-by-step instructions. " +
                "If they ask for dishes or food outside the app's built-in list, provide a full chef-standard recipe or answer! " +
                "Keep answers organized with emojis, clear headings, and concise steps."

        val historyContext = history.takeLast(4).joinToString("\n") { (role, msg) ->
            if (role == "user") "User: $msg" else "Mr. Foodie: $msg"
        }

        val fullPrompt = if (historyContext.isNotBlank()) {
            "$systemPrompt\n\nRecent Conversation:\n$historyContext\nUser: $question\nMr. Foodie:"
        } else {
            "$systemPrompt\n\nUser: $question\nMr. Foodie:"
        }

        val urlsToTry = listOf(hostUrl, "http://127.0.0.1:11434", "http://localhost:11434")

        for (url in urlsToTry) {
            try {
                val jsonPayload = JSONObject().apply {
                    put("model", preferredModel)
                    put("prompt", fullPrompt)
                    put("stream", false)
                }

                val request = Request.Builder()
                    .url("$url/api/generate")
                    .post(jsonPayload.toString().toRequestBody("application/json".toMediaType()))
                    .build()

                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                if (response.isSuccessful && !responseBody.isNullOrEmpty()) {
                    val root = JSONObject(responseBody)
                    val reply = root.optString("response", "").trim()
                    if (reply.isNotEmpty()) {
                        hostUrl = url
                        return@withContext reply
                    }
                }
            } catch (_: Exception) {
                // Try next url
            }
        }

        // Resilient smart response when Ollama server is offline or unreachable
        generateOfflineChefResponse(question, userName)
    }

    /**
     * Generates a structured Recipe using Ollama.
     */
    suspend fun generateRecipeWithOllama(
        userIngredients: List<String>,
        customInstruction: String? = null,
        userName: String = "Chef"
    ): Recipe? = withContext(Dispatchers.IO) {
        val prompt = "You are Mr. Foodie, master chef. Create a complete, delicious recipe based on: ${userIngredients.joinToString(", ")}. " +
                (if (!customInstruction.isNullOrBlank()) "Special request: $customInstruction. " else "") +
                "Return ONLY a valid JSON object (no markdown tags, no code fences) with keys: title, description, cuisine, prepTimeMinutes, cookTimeMinutes, servings, ingredients (array of {name, amount, unit}), steps (array of {stepNumber, instruction, timerSeconds}), calories, proteinGrams, carbsGrams, fatGrams."

        val urlsToTry = listOf(hostUrl, "http://127.0.0.1:11434", "http://localhost:11434")

        for (url in urlsToTry) {
            try {
                val jsonPayload = JSONObject().apply {
                    put("model", preferredModel)
                    put("prompt", prompt)
                    put("stream", false)
                    put("format", "json")
                }

                val request = Request.Builder()
                    .url("$url/api/generate")
                    .post(jsonPayload.toString().toRequestBody("application/json".toMediaType()))
                    .build()

                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                if (response.isSuccessful && !responseBody.isNullOrEmpty()) {
                    val root = JSONObject(responseBody)
                    val rawText = root.optString("response", "").trim()
                    val cleanJson = rawText.removePrefix("```json").removePrefix("```").removeSuffix("```").trim()
                    val parsed = JSONObject(cleanJson)

                    val ingArray = parsed.optJSONArray("ingredients") ?: JSONArray()
                    val recipeIngs = mutableListOf<RecipeIngredient>()
                    for (i in 0 until ingArray.length()) {
                        val o = ingArray.getJSONObject(i)
                        recipeIngs.add(
                            RecipeIngredient(
                                ingredientId = "ollama_ing_$i",
                                name = o.optString("name", "Ingredient"),
                                amount = o.optDouble("amount", 1.0).toFloat(),
                                unit = o.optString("unit", "pcs")
                            )
                        )
                    }

                    val stepsArray = parsed.optJSONArray("steps") ?: JSONArray()
                    val recipeSteps = mutableListOf<RecipeStep>()
                    for (i in 0 until stepsArray.length()) {
                        val o = stepsArray.getJSONObject(i)
                        val timer = if (o.has("timerSeconds")) o.optInt("timerSeconds") else null
                        recipeSteps.add(
                            RecipeStep(
                                stepNumber = o.optInt("stepNumber", i + 1),
                                instruction = o.optString("instruction", "Cook ingredient."),
                                timerSeconds = if (timer != null && timer > 0) timer else null
                            )
                        )
                    }

                    return@withContext Recipe(
                        id = "ollama_rec_${System.currentTimeMillis()}",
                        title = parsed.optString("title", "Mr. Foodie's Creation"),
                        description = parsed.optString("description", "A custom dish crafted by Mr. Foodie for $userName."),
                        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?auto=format&fit=crop&w=800&q=80",
                        cuisine = parsed.optString("cuisine", "Chef's Special"),
                        mealTypes = listOf(MealType.DINNER, MealType.LUNCH),
                        prepTimeMinutes = parsed.optInt("prepTimeMinutes", 15),
                        cookTimeMinutes = parsed.optInt("cookTimeMinutes", 25),
                        servings = parsed.optInt("servings", 2),
                        difficulty = DifficultyLevel.MEDIUM,
                        ingredients = recipeIngs,
                        steps = recipeSteps,
                        nutrition = NutritionInfo(
                            calories = parsed.optInt("calories", 450),
                            proteinGrams = parsed.optInt("proteinGrams", 32),
                            carbsGrams = parsed.optInt("carbsGrams", 38),
                            fatGrams = parsed.optInt("fatGrams", 15)
                        ),
                        tags = listOf("Ollama AI", "Mr. Foodie Special", preferredModel),
                        rating = 5.0f,
                        reviewsCount = 42
                    )
                }
            } catch (_: Exception) {
                // Continue
            }
        }
        null
    }

    /**
     * Fallback expert response when Ollama daemon is offline, keeping the UI informative and interactive.
     */
    private fun generateOfflineChefResponse(question: String, userName: String): String {
        val q = question.lowercase()
        return when {
            q.contains("substitute") || q.contains("swap") || q.contains("instead of") -> {
                "👨‍🍳 **Mr. Foodie's Substitution Guide for $userName**:\n\n" +
                        "• **Heavy Cream**: Whisk 3/4 cup whole milk + 1/3 cup melted unsalted butter, or use full-fat Greek yogurt.\n" +
                        "• **Eggs in Baking**: 1/4 cup unsweetened applesauce, 1/2 mashed ripe banana, or 1 tbsp chia seeds + 3 tbsp water per egg.\n" +
                        "• **Soy Sauce**: Tamari (gluten-free), coconut aminos (lower sodium), or Worcestershire sauce.\n" +
                        "• **Buttermilk**: 1 cup milk + 1 tbsp lemon juice or white vinegar (let sit 5 mins).\n\n" +
                        "💡 *Tip: Start your local Ollama server (`ollama run llama3.2`) to get infinite AI variations!*"
            }
            q.contains("protein") || q.contains("muscle") || q.contains("gym") -> {
                "💪 **Mr. Foodie's High-Protein Recommendation for $userName**:\n\n" +
                        "Try a **Gourmet Chicken Breast or Tofu Power Bowl**!\n" +
                        "• Sear seasoned chicken breast or firm tofu in olive oil with garlic, rosemary, and paprika (6 mins per side).\n" +
                        "• Pair with quinoa or brown rice, steamed broccoli, and a drizzle of lemon-tahini dressing.\n" +
                        "• **Macros**: ~45g Protein | 38g Carbs | 12g Healthy Fats | 440 kcal."
            }
            q.contains("dessert") || q.contains("sweet") || q.contains("chocolate") -> {
                "🍫 **Mr. Foodie's Quick Molten Lava Delight for $userName**:\n\n" +
                        "• Melt 100g dark chocolate with 50g butter.\n" +
                        "• Whisk 2 eggs with 2 tbsp sugar, fold in melted chocolate and 2 tbsp flour.\n" +
                        "• Bake in greased ramekins at 200°C (400°F) for 10-12 minutes until edges are set and center is gooey.\n" +
                        "• Dust with powdered sugar and serve hot!"
            }
            else -> {
                "👋 **Mr. Foodie at your service, $userName!**\n\n" +
                        "I'm here to help you cook anything your heart desires! Here's what I can do:\n" +
                        "• 🍳 **Ask for any recipe**: Ask me how to make any dish from Indian Butter Chicken to Italian Risotto, French Souffle, or Japanese Ramen.\n" +
                        "• 🔄 **Smart Ingredient Substitutions**: Need a swap for eggs, dairy, or wine? Just ask!\n" +
                        "• ⏱️ **Cooking Techniques & Times**: Perfect steak temps, knife skills, or resting times.\n\n" +
                        "*(To enable local offline LLM responses, run `ollama run llama3.2` on port 11434)*"
            }
        }
    }
}
