package com.example.ui.screens.results

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MealType
import com.example.ui.components.RecipeCard
import com.example.ui.theme.HerbGreen
import com.example.ui.theme.TerracottaPrimary
import com.example.viewmodel.FilterState
import com.example.viewmodel.MainViewModel
import com.example.viewmodel.RecipeSortOption

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RecipeResultsScreen(
    viewModel: MainViewModel,
    onBack: () -> Unit,
    onNavigateToDetail: (String) -> Unit
) {
    val matches by viewModel.recipeMatches.collectAsState()
    val filterState by viewModel.filterState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val favoriteIds by viewModel.favoriteIds.collectAsState()

    var showFilterSheet by remember { mutableStateOf(false) }
    var isGridView by remember { mutableStateOf(false) }

    val activeFilterCount = remember(filterState) {
        var count = 0
        if (filterState.cuisine != "All") count++
        if (filterState.mealType != null) count++
        if (filterState.maxCookingTime != null) count++
        if (filterState.difficulty != "All") count++
        count += filterState.dietaryFilters.size
        count
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Recipes You Can Make",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    // View Toggle (Grid vs List)
                    IconButton(onClick = { isGridView = !isGridView }) {
                        Icon(
                            imageVector = if (isGridView) Icons.Default.ViewList else Icons.Default.GridView,
                            contentDescription = "Toggle layout"
                        )
                    }

                    // Filter Button with Badge
                    IconButton(
                        onClick = { showFilterSheet = true },
                        modifier = Modifier.testTag("filter_button")
                    ) {
                        BadgedBox(
                            badge = {
                                if (activeFilterCount > 0) {
                                    Badge(containerColor = TerracottaPrimary) {
                                        Text("$activeFilterCount")
                                    }
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.FilterList, contentDescription = "Filters")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Search Input Field
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.updateSearchQuery(it) },
                    placeholder = { Text("Search recipes, cuisines, tags...") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.updateSearchQuery("") }) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth().testTag("results_search_input")
                )
            }

            // Results summary bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${matches.size} recipes found",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Sorted by: ${filterState.sortOption.displayName}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Content Feed
            if (matches.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "No matching recipes found",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Try clearing some filters or adding more ingredients you have on hand.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.resetFilters() },
                            colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Reset Filters")
                        }
                    }
                }
            } else if (isGridView) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(matches, key = { it.recipe.id }) { match ->
                        RecipeCard(
                            matchResult = match,
                            isFavorite = favoriteIds.contains(match.recipe.id),
                            onFavoriteToggle = { viewModel.toggleFavorite(match.recipe.id) },
                            onClick = { onNavigateToDetail(match.recipe.id) }
                        )
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(matches, key = { it.recipe.id }) { match ->
                        RecipeCard(
                            matchResult = match,
                            isFavorite = favoriteIds.contains(match.recipe.id),
                            onFavoriteToggle = { viewModel.toggleFavorite(match.recipe.id) },
                            onClick = { onNavigateToDetail(match.recipe.id) }
                        )
                    }
                }
            }
        }
    }

    // Modal Bottom Sheet for Filters
    if (showFilterSheet) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = { showFilterSheet = false },
            sheetState = sheetState
        ) {
            FilterBottomSheetContent(
                currentFilter = filterState,
                onApply = { updated ->
                    viewModel.updateFilter { updated }
                    showFilterSheet = false
                },
                onReset = {
                    viewModel.resetFilters()
                    showFilterSheet = false
                }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterBottomSheetContent(
    currentFilter: FilterState,
    onApply: (FilterState) -> Unit,
    onReset: () -> Unit
) {
    var state by remember { mutableStateOf(currentFilter) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Filter Recipes",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = onReset) {
                Text("Reset All")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sort By
        Text(text = "Sort By", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            RecipeSortOption.values().forEach { option ->
                FilterChip(
                    selected = state.sortOption == option,
                    onClick = { state = state.copy(sortOption = option) },
                    label = { Text(option.displayName) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = TerracottaPrimary,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cuisine
        Text(text = "Cuisine", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf("All", "Italian", "Indian", "Mediterranean", "Asian", "American", "French", "Mexican", "Chinese").forEach { cui ->
                FilterChip(
                    selected = state.cuisine == cui,
                    onClick = { state = state.copy(cuisine = cui) },
                    label = { Text(cui) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Meal Type
        Text(text = "Meal Type", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf(null to "Any", MealType.BREAKFAST to "Breakfast", MealType.LUNCH to "Lunch", MealType.DINNER to "Dinner", MealType.SNACK to "Snack").forEach { (mt, label) ->
                FilterChip(
                    selected = state.mealType == mt,
                    onClick = { state = state.copy(mealType = mt) },
                    label = { Text(label) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Max Cooking Time
        Text(text = "Max Cooking Time", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf(null to "Any", 15 to "< 15 min", 30 to "< 30 min", 45 to "< 45 min", 60 to "< 60 min").forEach { (time, label) ->
                FilterChip(
                    selected = state.maxCookingTime == time,
                    onClick = { state = state.copy(maxCookingTime = time) },
                    label = { Text(label) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dietary
        Text(text = "Dietary Restrictions", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf("Vegetarian", "Vegan", "High protein", "Low carb", "Gluten free", "Keto").forEach { diet ->
                val isSel = state.dietaryFilters.contains(diet)
                FilterChip(
                    selected = isSel,
                    onClick = {
                        val newSet = state.dietaryFilters.toMutableSet()
                        if (isSel) newSet.remove(diet) else newSet.add(diet)
                        state = state.copy(dietaryFilters = newSet)
                    },
                    label = { Text(diet) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onApply(state) },
            colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("apply_filters_btn")
        ) {
            Text("Apply Filters", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}
