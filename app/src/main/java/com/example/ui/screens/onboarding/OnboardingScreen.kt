package com.example.ui.screens.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import com.example.data.sample.SeedData
import com.example.ui.theme.HerbGreen
import com.example.ui.theme.TerracottaPrimary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OnboardingScreen(
    onComplete: (
        name: String,
        diet: List<String>,
        allergies: List<String>,
        skill: String,
        time: Int,
        starterPantry: List<String>
    ) -> Unit
) {
    var step by remember { mutableIntStateOf(0) }
    var userName by remember { mutableStateOf("Home Chef") }
    val selectedDiets = remember { mutableStateListOf<String>() }
    val selectedAllergies = remember { mutableStateListOf<String>() }
    var customAllergyInput by remember { mutableStateOf("") }
    var selectedSkill by remember { mutableStateOf("Intermediate") }
    var selectedTimeMinutes by remember { mutableIntStateOf(30) }
    val selectedStaples = remember {
        mutableStateListOf(
            "Rice", "Eggs", "Chicken", "Potato", "Tomato", "Onion", "Garlic"
        )
    }

    val totalSteps = 6

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (step > 0) {
                        IconButton(onClick = { step-- }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.size(48.dp))
                    }

                    Text(
                        text = "Step ${step + 1} of $totalSteps",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    if (step < totalSteps - 1) {
                        TextButton(onClick = { step = totalSteps - 1 }) {
                            Text("Skip", color = MaterialTheme.colorScheme.primary)
                        }
                    } else {
                        Spacer(modifier = Modifier.size(48.dp))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { (step + 1).toFloat() / totalSteps.toFloat() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    color = TerracottaPrimary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        },
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (step > 0) {
                        TextButton(
                            onClick = { step-- },
                            modifier = Modifier.height(50.dp)
                        ) {
                            Text("Previous", fontSize = 16.sp)
                        }
                    } else {
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    Button(
                        onClick = {
                            if (step < totalSteps - 1) {
                                step++
                            } else {
                                onComplete(
                                    userName,
                                    selectedDiets.toList(),
                                    selectedAllergies.toList(),
                                    selectedSkill,
                                    selectedTimeMinutes,
                                    selectedStaples.toList()
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .height(52.dp)
                            .padding(horizontal = 8.dp)
                            .testTag("onboarding_next_btn")
                    ) {
                        Text(
                            text = if (step == totalSteps - 1) "Let's Cook!" else "Continue",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        AnimatedContent(
            targetState = step,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally { width -> width } + fadeIn() togetherWith
                            slideOutHorizontally { width -> -width } + fadeOut()
                } else {
                    slideInHorizontally { width -> -width } + fadeIn() togetherWith
                            slideOutHorizontally { width -> width } + fadeOut()
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) { targetStep ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                when (targetStep) {
                    // Step 0: Welcome
                    0 -> {
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp)
                            ) {
                                Surface(
                                    color = TerracottaPrimary.copy(alpha = 0.12f),
                                    shape = CircleShape,
                                    modifier = Modifier.size(100.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.RestaurantMenu,
                                        contentDescription = null,
                                        tint = TerracottaPrimary,
                                        modifier = Modifier
                                            .padding(24.dp)
                                            .fillMaxSize()
                                    )
                                }
                            }

                            Text(
                                text = "Cook with what you already have.",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Turn the ingredients in your kitchen into delicious meals without the guesswork.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(32.dp))
                            Text(
                                text = "What should we call you?",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = userName,
                                onValueChange = { userName = it },
                                label = { Text("Your Name") },
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth().testTag("user_name_input")
                            )
                        }
                    }

                    // Step 1: Dietary Preferences
                    1 -> {
                        item {
                            Text(
                                text = "Any dietary preferences?",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "We'll tailor your recipe feed and hide anything that doesn't fit your diet.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                SeedData.dietaryPreferencesList.forEach { diet ->
                                    val isSelected = selectedDiets.contains(diet)
                                    Surface(
                                        color = if (isSelected) TerracottaPrimary else MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(16.dp),
                                        border = BorderStroke(
                                            1.dp,
                                            if (isSelected) TerracottaPrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
                                        ),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(16.dp))
                                            .clickable {
                                                if (diet == "No preference") {
                                                    selectedDiets.clear()
                                                    selectedDiets.add("No preference")
                                                } else {
                                                    selectedDiets.remove("No preference")
                                                    if (isSelected) selectedDiets.remove(diet) else selectedDiets.add(diet)
                                                }
                                            }
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                                        ) {
                                            if (isSelected) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                                Spacer(modifier = Modifier.width(6.dp))
                                            }
                                            Text(
                                                text = diet,
                                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                                fontWeight = FontWeight.Medium
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Step 2: Allergies
                    2 -> {
                        item {
                            Text(
                                text = "Any food allergies?",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Safety first: recipes with these ingredients will NEVER be recommended.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                SeedData.commonAllergies.forEach { allergy ->
                                    val isSelected = selectedAllergies.contains(allergy)
                                    Surface(
                                        color = if (isSelected) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(16.dp),
                                        border = BorderStroke(
                                            1.dp,
                                            if (isSelected) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
                                        ),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(16.dp))
                                            .clickable {
                                                if (isSelected) selectedAllergies.remove(allergy) else selectedAllergies.add(allergy)
                                            }
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                                        ) {
                                            if (isSelected) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                                Spacer(modifier = Modifier.width(6.dp))
                                            }
                                            Text(
                                                text = allergy,
                                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                                fontWeight = FontWeight.Medium
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                OutlinedTextField(
                                    value = customAllergyInput,
                                    onValueChange = { customAllergyInput = it },
                                    label = { Text("Add custom allergen") },
                                    singleLine = true,
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        if (customAllergyInput.isNotBlank()) {
                                            selectedAllergies.add(customAllergyInput.trim())
                                            customAllergyInput = ""
                                        }
                                    },
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text("Add")
                                }
                            }
                        }
                    }

                    // Step 3: Cooking Skill
                    3 -> {
                        item {
                            Text(
                                text = "What's your cooking skill level?",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "We'll suggest recipes with techniques matched to your comfort.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            listOf(
                                Triple("Beginner", "Quick, simple steps and minimal prep work", "🍳"),
                                Triple("Intermediate", "Comfortable with standard pan cooking and sauteing", "🥘"),
                                Triple("Advanced", "Love intricate sauces, multi-step baking and techniques", "👨‍🍳")
                            ).forEach { (skill, desc, emoji) ->
                                val isSelected = selectedSkill == skill
                                Surface(
                                    color = if (isSelected) TerracottaPrimary.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(16.dp),
                                    border = BorderStroke(
                                        if (isSelected) 2.dp else 1.dp,
                                        if (isSelected) TerracottaPrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                        .clickable { selectedSkill = skill }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(16.dp)
                                    ) {
                                        Text(text = emoji, fontSize = 28.sp)
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = skill,
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = desc,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        if (isSelected) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                tint = TerracottaPrimary,
                                                modifier = Modifier.size(22.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Step 4: Cooking Time
                    4 -> {
                        item {
                            Text(
                                text = "Preferred cooking time?",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "How much time do you usually like to spend making a meal?",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            listOf(
                                Pair(15, "Under 15 minutes — Quick and lightning-fast"),
                                Pair(30, "15 - 30 minutes — Balanced everyday dinners"),
                                Pair(60, "30 - 60 minutes — Thorough and comforting"),
                                Pair(90, "60+ minutes — Slow simmering & weekend projects")
                            ).forEach { (time, label) ->
                                val isSelected = selectedTimeMinutes == time
                                Surface(
                                    color = if (isSelected) HerbGreen.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(16.dp),
                                    border = BorderStroke(
                                        if (isSelected) 2.dp else 1.dp,
                                        if (isSelected) HerbGreen else MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                        .clickable { selectedTimeMinutes = time }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(16.dp)
                                    ) {
                                        Text(
                                            text = "$time min",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp,
                                            color = if (isSelected) HerbGreen else MaterialTheme.colorScheme.onSurface
                                        )
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Text(
                                            text = label,
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.weight(1f)
                                        )
                                        if (isSelected) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                tint = HerbGreen,
                                                modifier = Modifier.size(22.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Step 5: Starter Pantry Staples
                    5 -> {
                        item {
                            Text(
                                text = "What's in your kitchen today?",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Select ingredients you currently have so we can immediately show you meals you can make right now.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                SeedData.starterPantry.forEach { staple ->
                                    val isSelected = selectedStaples.contains(staple)
                                    Surface(
                                        color = if (isSelected) TerracottaPrimary else MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(16.dp),
                                        border = BorderStroke(
                                            1.dp,
                                            if (isSelected) TerracottaPrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
                                        ),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(16.dp))
                                            .clickable {
                                                if (isSelected) selectedStaples.remove(staple) else selectedStaples.add(staple)
                                            }
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                                        ) {
                                            if (isSelected) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                                Spacer(modifier = Modifier.width(6.dp))
                                            }
                                            Text(
                                                text = staple,
                                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                                fontWeight = FontWeight.Medium
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }
        }
    }
}
