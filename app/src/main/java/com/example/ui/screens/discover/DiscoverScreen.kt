package com.example.ui.screens.discover

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.engine.RecipeMatchingEngine
import com.example.data.model.Recipe
import com.example.ui.components.RecipeCard
import com.example.ui.components.SectionHeader
import com.example.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreen(
    viewModel: MainViewModel,
    onNavigateToDetail: (String) -> Unit
) {
    val allRecipes = remember { viewModel.recipeRepository.getAllRecipes() }
    val selectedIngredients by viewModel.selectedIngredients.collectAsState()
    val favoriteIds by viewModel.favoriteIds.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoryTag by remember { mutableStateOf("All") }

    val categories = listOf(
        "All", "Quick & Easy", "High Protein", "Under 30 Min",
        "Italian Classics", "Indian Delights", "Vegetarian", "Budget Friendly"
    )

    // Filtered recipes
    val displayedRecipes = remember(searchQuery, selectedCategoryTag) {
        val q = searchQuery.trim().lowercase()
        allRecipes.filter { recipe ->
            val matchesQuery = q.isEmpty() ||
                    recipe.title.lowercase().contains(q) ||
                    recipe.cuisine.lowercase().contains(q) ||
                    recipe.tags.any { it.lowercase().contains(q) } ||
                    recipe.ingredients.any { it.name.lowercase().contains(q) }

            val matchesCat = when (selectedCategoryTag) {
                "Quick & Easy" -> recipe.totalTimeMinutes <= 20
                "High Protein" -> recipe.isHighProtein
                "Under 30 Min" -> recipe.totalTimeMinutes <= 30
                "Italian Classics" -> recipe.cuisine.equals("Italian", ignoreCase = true)
                "Indian Delights" -> recipe.cuisine.equals("Indian", ignoreCase = true)
                "Vegetarian" -> recipe.isVegetarian
                "Budget Friendly" -> recipe.tags.any { it.contains("Budget", ignoreCase = true) }
                else -> true
            }

            matchesQuery && matchesCat
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("discover_screen"),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Header
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Discover Recipes",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Explore culinary inspiration across cuisines & diets",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Search bar
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search by recipe name, cuisine, ingredients...") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .testTag("discover_search_input")
            )
        }

        // Category Pills
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categories) { cat ->
                    FilterChip(
                        selected = selectedCategoryTag == cat,
                        onClick = { selectedCategoryTag = cat },
                        label = { Text(cat) }
                    )
                }
            }
        }

        if (searchQuery.isNotBlank() || selectedCategoryTag != "All") {
            // Filtered Grid / Feed
            item {
                SectionHeader(
                    title = "$selectedCategoryTag (${displayedRecipes.size})",
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            items(displayedRecipes, key = { it.id }) { recipe ->
                val match = RecipeMatchingEngine.calculateMatch(recipe, selectedIngredients)
                RecipeCard(
                    matchResult = match,
                    isFavorite = favoriteIds.contains(recipe.id),
                    onFavoriteToggle = { viewModel.toggleFavorite(recipe.id) },
                    onClick = { onNavigateToDetail(recipe.id) },
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        } else {
            // Curated Sections
            // 1. Trending
            item {
                CuratedSectionRow(
                    title = "🔥 Trending This Week",
                    recipes = allRecipes.filter { it.rating >= 4.8f }.take(5),
                    selectedIngredients = selectedIngredients,
                    favoriteIds = favoriteIds,
                    onFavoriteToggle = { viewModel.toggleFavorite(it) },
                    onNavigateToDetail = onNavigateToDetail
                )
            }

            // 2. Quick & Easy (<20 mins)
            item {
                CuratedSectionRow(
                    title = "⚡ Quick & Easy (< 20 mins)",
                    recipes = allRecipes.filter { it.totalTimeMinutes <= 20 }.take(5),
                    selectedIngredients = selectedIngredients,
                    favoriteIds = favoriteIds,
                    onFavoriteToggle = { viewModel.toggleFavorite(it) },
                    onNavigateToDetail = onNavigateToDetail
                )
            }

            // 3. High Protein Power
            item {
                CuratedSectionRow(
                    title = "💪 High Protein Meals",
                    recipes = allRecipes.filter { it.isHighProtein }.take(5),
                    selectedIngredients = selectedIngredients,
                    favoriteIds = favoriteIds,
                    onFavoriteToggle = { viewModel.toggleFavorite(it) },
                    onNavigateToDetail = onNavigateToDetail
                )
            }

            // 4. Italian Classics
            item {
                CuratedSectionRow(
                    title = "🍝 Italian Classics",
                    recipes = allRecipes.filter { it.cuisine.equals("Italian", ignoreCase = true) }.take(5),
                    selectedIngredients = selectedIngredients,
                    favoriteIds = favoriteIds,
                    onFavoriteToggle = { viewModel.toggleFavorite(it) },
                    onNavigateToDetail = onNavigateToDetail
                )
            }

            // 5. Indian Delights
            item {
                CuratedSectionRow(
                    title = "🍛 Indian Delights",
                    recipes = allRecipes.filter { it.cuisine.equals("Indian", ignoreCase = true) }.take(5),
                    selectedIngredients = selectedIngredients,
                    favoriteIds = favoriteIds,
                    onFavoriteToggle = { viewModel.toggleFavorite(it) },
                    onNavigateToDetail = onNavigateToDetail
                )
            }
        }
    }
}

@Composable
fun CuratedSectionRow(
    title: String,
    recipes: List<Recipe>,
    selectedIngredients: List<String>,
    favoriteIds: List<String>,
    onFavoriteToggle: (String) -> Unit,
    onNavigateToDetail: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SectionHeader(
            title = title,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(recipes, key = { it.id }) { recipe ->
                val match = RecipeMatchingEngine.calculateMatch(recipe, selectedIngredients)
                RecipeCard(
                    matchResult = match,
                    isFavorite = favoriteIds.contains(recipe.id),
                    onFavoriteToggle = { onFavoriteToggle(recipe.id) },
                    onClick = { onNavigateToDetail(recipe.id) },
                    modifier = Modifier.width(260.dp)
                )
            }
        }
    }
}
