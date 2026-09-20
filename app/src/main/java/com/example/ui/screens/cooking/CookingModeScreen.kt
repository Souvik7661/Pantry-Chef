package com.example.ui.screens.cooking

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.ui.components.ActiveTimersBar
import com.example.ui.theme.HerbGreen
import com.example.ui.theme.HoneySaffron
import com.example.ui.theme.TerracottaPrimary
import com.example.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookingModeScreen(
    recipeId: String,
    viewModel: MainViewModel,
    onClose: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val recipe = remember(recipeId) {
        viewModel.recipeRepository.getRecipeById(recipeId)
            ?: viewModel.aiGeneratedRecipe.value
    }

    if (recipe == null || recipe.steps.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Recipe steps unavailable")
        }
        return
    }

    val activeTimers by viewModel.activeTimers.collectAsState()
    val ttsReady by viewModel.ttsReady.collectAsState()

    var currentStepIndex by remember { mutableIntStateOf(0) }
    var showRatingDialog by remember { mutableStateOf(false) }

    val step = recipe.steps[currentStepIndex]
    val totalSteps = recipe.steps.size
    val progress = (currentStepIndex + 1).toFloat() / totalSteps.toFloat()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = recipe.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                        Text(
                            text = "STEP ${currentStepIndex + 1} OF $totalSteps",
                            style = MaterialTheme.typography.labelSmall,
                            color = TerracottaPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Exit cooking mode")
                    }
                },
                actions = {
                    // Audio readout TTS
                    IconButton(
                        onClick = { viewModel.speakInstruction(step.instruction) },
                        modifier = Modifier.testTag("read_aloud_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Read aloud",
                            tint = TerracottaPrimary
                        )
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Previous Step Button
                    OutlinedButton(
                        onClick = {
                            if (currentStepIndex > 0) {
                                currentStepIndex--
                                viewModel.stopSpeaking()
                            }
                        },
                        enabled = currentStepIndex > 0,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .height(52.dp)
                            .weight(1f)
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Previous")
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Next / Finish Button
                    Button(
                        onClick = {
                            viewModel.stopSpeaking()
                            if (currentStepIndex < totalSteps - 1) {
                                currentStepIndex++
                            } else {
                                showRatingDialog = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (currentStepIndex == totalSteps - 1) HerbGreen else TerracottaPrimary
                        ),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .height(52.dp)
                            .weight(1.3f)
                            .testTag("next_step_btn")
                    ) {
                        Text(
                            text = if (currentStepIndex == totalSteps - 1) "I'm Done Cooking! 🎉" else "Next Step",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        if (currentStepIndex < totalSteps - 1) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Linear Step Progress Bar
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp),
                color = TerracottaPrimary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            // Active Timers Bar if any timers exist
            if (activeTimers.isNotEmpty()) {
                ActiveTimersBar(
                    timers = activeTimers,
                    onTogglePause = { viewModel.pauseTimer(it) },
                    onCancel = { viewModel.cancelTimer(it) }
                )
            }

            // Distraction-free Cooking Step Content
            AnimatedContent(
                targetState = step,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) { currentStep ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Big readable step header
                    Text(
                        text = "Step ${currentStep.stepNumber}",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    // Large readable instruction text
                    Text(
                        text = currentStep.instruction,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 36.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    // Integrated timer button if step includes timed duration
                    if (currentStep.timerSeconds != null && currentStep.timerSeconds > 0) {
                        val minutes = currentStep.timerSeconds / 60
                        val timerLabel = if (minutes > 0) "$minutes min timer" else "${currentStep.timerSeconds}s timer"

                        Button(
                            onClick = {
                                viewModel.startTimer(
                                    title = "Step ${currentStep.stepNumber}: ${recipe.title}",
                                    seconds = currentStep.timerSeconds
                                )
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp)
                                .testTag("start_step_timer_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Start $timerLabel",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Chef tip card
                    if (currentStep.tip != null) {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = HoneySaffron.copy(alpha = 0.12f)
                            ),
                            border = BorderStroke(1.dp, HoneySaffron.copy(alpha = 0.5f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = HoneySaffron,
                                    modifier = Modifier.size(22.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "Chef's Tip",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = HoneySaffron
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = currentStep.tip,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }

                    // Quick Ingredient Reference Drawer / List
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Ingredient Checklist",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            recipe.ingredients.forEach { ing ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 3.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = ing.name,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = "${ing.amount} ${ing.unit}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Done Cooking & Review Dialog
    if (showRatingDialog) {
        var rating by remember { mutableIntStateOf(5) }
        var wouldCookAgain by remember { mutableStateOf(true) }
        var notes by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showRatingDialog = false },
            title = {
                Text(
                    text = "Bon Appétit! 🍽️",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "You just cooked ${recipe.title}. How was it?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // 5 Star Rating Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        (1..5).forEach { star ->
                            IconButton(onClick = { rating = star }) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "$star stars",
                                    tint = if (star <= rating) Color(0xFFFBC02D) else MaterialTheme.colorScheme.outline,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Would cook again switch
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Would you cook this again?",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                        Switch(
                            checked = wouldCookAgain,
                            onCheckedChange = { wouldCookAgain = it }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = notes,
                        onValueChange = { notes = it },
                        label = { Text("Notes & Adjustments (optional)") },
                        placeholder = { Text("e.g. Added extra garlic, was delicious!") },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.recipeRepository.recordCookedMeal(
                                recipe = recipe,
                                rating = rating,
                                wouldCookAgain = wouldCookAgain,
                                notes = notes
                            )
                        }
                        showRatingDialog = false
                        onClose()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HerbGreen),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.testTag("save_cooked_meal_btn")
                ) {
                    Text("Save to Cooking Log")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showRatingDialog = false
                    onClose()
                }) {
                    Text("Skip")
                }
            }
        )
    }
}
