package com.example.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.sample.SeedData
import com.example.ui.components.ActiveTimersContainer
import com.example.ui.components.FeaturedPlatedDishCard
import com.example.ui.components.IngredientChip
import com.example.ui.components.RecipeCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.AmberGoldOnPrimary
import com.example.ui.theme.AmberGoldPrimary
import com.example.ui.theme.DarkCardBorder
import com.example.ui.theme.DarkPillBg
import com.example.ui.theme.HerbGreen
import com.example.ui.theme.HoneySaffron
import com.example.ui.theme.IvoryTextPrimary
import com.example.ui.theme.MutedSlateText
import com.example.ui.theme.TerracottaPrimary
import com.example.viewmodel.MainViewModel
import java.util.Calendar

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onNavigateToResults: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToPantry: () -> Unit,
    onNavigateToAiGenerator: () -> Unit
) {
    val selectedIngredients by viewModel.selectedIngredients.collectAsState()
    val recipeMatches by viewModel.recipeMatches.collectAsState()
    val expiringPantry by viewModel.expiringPantryItems.collectAsState()
    val kitchenRescueMatch by viewModel.kitchenRescueRecommendation.collectAsState()
    val favoriteIds by viewModel.favoriteIds.collectAsState()
    val userPrefs by viewModel.userPreferences.collectAsState()

    val expiringMatches = remember(recipeMatches) {
        recipeMatches.filter { it.usesExpiringIngredients }
    }
    val topMatches = remember(recipeMatches) {
        recipeMatches.take(6)
    }

    var inputIngredientText by remember { mutableStateOf("") }
    var showCameraDialog by remember { mutableStateOf(false) }
    var showVoiceDialog by remember { mutableStateOf(false) }
    var showRescueDialog by remember { mutableStateOf(false) }

    // Time of day greeting
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val greeting = when (hour) {
        in 5..11 -> "Good morning"
        in 12..16 -> "Good afternoon"
        else -> "Good evening"
    }
    val chefName = userPrefs?.userName ?: "Chef"

    // Autocomplete suggestions
    val suggestions = remember(inputIngredientText, selectedIngredients) {
        if (inputIngredientText.trim().length >= 2) {
            SeedData.ingredientsList
                .map { it.name }
                .filter { it.contains(inputIngredientText.trim(), ignoreCase = true) }
                .filterNot { selectedIngredients.any { s -> s.equals(it, ignoreCase = true) } }
                .take(4)
        } else {
            emptyList()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Active Timers Bar (Isolated from full HomeScreen recomposition)
        item(key = "active_timers_container") {
            ActiveTimersContainer(viewModel = viewModel)
        }

        // Header Greeting
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "$greeting, $chefName! 👋",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "What's in your kitchen today?",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Mr. Foodie "Ask Me" shortcut button
                    Surface(
                        color = AmberGoldPrimary.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, AmberGoldPrimary.copy(alpha = 0.45f)),
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable(onClick = onNavigateToAiGenerator)
                            .testTag("ai_generator_btn")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_mr_foodie_robot),
                                contentDescription = "Ask Me",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Ask Me",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = AmberGoldPrimary
                            )
                        }
                    }
                }
            }
        }

        // Featured Plated Dish Showcase (Gourmet circular plate presentation from reference UI)
        topMatches.firstOrNull()?.let { featuredMatch ->
            item(key = "featured_plated_spotlight") {
                Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                    FeaturedPlatedDishCard(
                        matchResult = featuredMatch,
                        isFavorite = favoriteIds.contains(featuredMatch.recipe.id),
                        onFavoriteToggle = { viewModel.toggleFavorite(featuredMatch.recipe.id) },
                        onClick = { onNavigateToDetail(featuredMatch.recipe.id) }
                    )
                }
            }
        }

        // Kitchen Rescue Banner (if expiring items exist)
        if (kitchenRescueMatch != null && expiringPantry.isNotEmpty()) {
            item {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = AmberGoldPrimary.copy(alpha = 0.12f)
                    ),
                    border = BorderStroke(1.5.dp, AmberGoldPrimary.copy(alpha = 0.6f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .clickable { showRescueDialog = true }
                        .testTag("kitchen_rescue_card")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Surface(
                            color = AmberGoldPrimary,
                            shape = CircleShape,
                            modifier = Modifier.size(44.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = null,
                                tint = AmberGoldOnPrimary,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Kitchen Rescue 🛟",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "${expiringPantry.size} item(s) nearing expiry. Rescue with \"${kitchenRescueMatch?.recipe?.title}\"!",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 2
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = AmberGoldPrimary
                        )
                    }
                }
            }
        }

        // Main Ingredient Input Card
        item {
            Card(
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Your Available Ingredients",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Add what you have or scan with camera",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Text Input + Add
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = inputIngredientText,
                            onValueChange = { inputIngredientText = it },
                            placeholder = { Text("e.g. Chicken, Tomato, Rice...") },
                            singleLine = true,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("ingredient_input_field")
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                if (inputIngredientText.isNotBlank()) {
                                    viewModel.addIngredient(inputIngredientText)
                                    inputIngredientText = ""
                                }
                            },
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AmberGoldPrimary,
                                contentColor = AmberGoldOnPrimary
                            ),
                            modifier = Modifier.testTag("add_ingredient_btn")
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                        }
                    }

                    // Autocomplete suggestions
                    if (suggestions.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            suggestions.forEach { sug ->
                                Surface(
                                    color = DarkPillBg,
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(1.dp, DarkCardBorder),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable {
                                            viewModel.addIngredient(sug)
                                            inputIngredientText = ""
                                        }
                                ) {
                                    Text(
                                        text = "+ $sug",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Medium,
                                        color = IvoryTextPrimary,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Chips of currently selected ingredients
                    if (selectedIngredients.isEmpty()) {
                        Surface(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "No ingredients selected yet. Add some above or use pantry items!",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    } else {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            selectedIngredients.forEach { ing ->
                                IngredientChip(
                                    name = ing,
                                    onRemove = { viewModel.removeIngredient(ing) },
                                    modifier = Modifier.testTag("chip_$ing")
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Quick Action Utility Buttons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = { showCameraDialog = true },
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.weight(1f).testTag("scan_camera_btn")
                        ) {
                            Icon(imageVector = Icons.Default.CameraAlt, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Scan", fontSize = 12.sp)
                        }

                        OutlinedButton(
                            onClick = { showVoiceDialog = true },
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.weight(1f).testTag("voice_input_btn")
                        ) {
                            Icon(imageVector = Icons.Default.Mic, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Voice", fontSize = 12.sp)
                        }

                        OutlinedButton(
                            onClick = { viewModel.useAllPantryIngredients() },
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.weight(1.2f).testTag("use_pantry_btn")
                        ) {
                            Icon(imageVector = Icons.Default.Kitchen, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("All Pantry", fontSize = 12.sp)
                        }

                        if (selectedIngredients.isNotEmpty()) {
                            IconButton(
                                onClick = { viewModel.clearIngredients() },
                                modifier = Modifier.size(40.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear all",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Prominent Primary Action: "What Can I Cook?"
                    Button(
                        onClick = onNavigateToResults,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AmberGoldPrimary,
                            contentColor = AmberGoldOnPrimary
                        ),
                        shape = RoundedCornerShape(26.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("what_can_i_cook_btn")
                    ) {
                        Icon(imageVector = Icons.Default.RestaurantMenu, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (selectedIngredients.isEmpty()) "Select Ingredients to Cook" else "What Can I Cook? (${recipeMatches.size} Recipes)",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Top Matches Carousel or Empty Selection Prompt
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                if (topMatches.isNotEmpty()) {
                    SectionHeader(
                        title = "Top Recipe Matches",
                        subtitle = "Ranked by how many ingredients you have",
                        actionLabel = "See All",
                        onAction = onNavigateToResults,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(topMatches, key = { it.recipe.id }) { match ->
                            RecipeCard(
                                matchResult = match,
                                isFavorite = favoriteIds.contains(match.recipe.id),
                                onFavoriteToggle = { viewModel.toggleFavorite(match.recipe.id) },
                                onClick = { onNavigateToDetail(match.recipe.id) },
                                modifier = Modifier.width(260.dp)
                            )
                        }
                    }
                } else {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "🍳 Ready to Cook, $chefName?",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Choose or type ingredients above, or tap 'All Pantry' to see what delicious dishes match your kitchen stock.",
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Use Before Expiry Section (if any expiring ingredients)
        if (expiringMatches.isNotEmpty()) {
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    SectionHeader(
                        title = "Use Before Expiry ⏳",
                        subtitle = "Dishes that make use of your nearing-expiry ingredients",
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(expiringMatches, key = { it.recipe.id }) { match ->
                            RecipeCard(
                                matchResult = match,
                                isFavorite = favoriteIds.contains(match.recipe.id),
                                onFavoriteToggle = { viewModel.toggleFavorite(match.recipe.id) },
                                onClick = { onNavigateToDetail(match.recipe.id) },
                                modifier = Modifier.width(260.dp)
                            )
                        }
                    }
                }
            }
        }

        // Quick Pantry Overview Card
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .clickable(onClick = onNavigateToPantry)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = HerbGreen.copy(alpha = 0.2f),
                            shape = CircleShape,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Kitchen,
                                contentDescription = null,
                                tint = HerbGreen,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Your Kitchen Pantry",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Manage inventory and expiry alerts",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }

    // Dialogs
    if (showCameraDialog) {
        CameraScannerDialog(
            onDismiss = { showCameraDialog = false },
            onAnalyze = { bmp -> viewModel.analyzePhotoForIngredients(bmp) },
            onAddIngredients = { list ->
                list.forEach { viewModel.addIngredient(it) }
            }
        )
    }

    if (showVoiceDialog) {
        VoiceInputDialog(
            onDismiss = { showVoiceDialog = false },
            onAddIngredients = { list ->
                list.forEach { viewModel.addIngredient(it) }
            }
        )
    }

    if (showRescueDialog && kitchenRescueMatch != null) {
        KitchenRescueDialog(
            rescueMatch = kitchenRescueMatch!!,
            onCookNow = { onNavigateToDetail(it) },
            onDismiss = { showRescueDialog = false }
        )
    }
}
