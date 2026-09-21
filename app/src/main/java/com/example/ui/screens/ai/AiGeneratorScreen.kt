package com.example.ui.screens.ai

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.IngredientChip
import com.example.ui.theme.AmberGoldOnPrimary
import com.example.ui.theme.AmberGoldPrimary
import com.example.ui.theme.DarkCardBorder
import com.example.ui.theme.DarkCardElevated
import com.example.ui.theme.DarkCardSurface
import com.example.ui.theme.DarkPillBg
import com.example.ui.theme.IvoryTextPrimary
import com.example.ui.theme.MutedSlateText
import com.example.ui.theme.ObsidianBlack
import com.example.ui.theme.TerracottaPrimary
import com.example.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AiGeneratorScreen(
    viewModel: MainViewModel,
    onBack: () -> Unit,
    onNavigateToDetail: (String) -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val userPrefs by viewModel.userPreferences.collectAsState()
    val userName = userPrefs?.userName ?: "Chef"

    val selectedIngredients by viewModel.selectedIngredients.collectAsState()
    val isGenerating by viewModel.isGeneratingRecipe.collectAsState()
    val generatedRecipe by viewModel.aiGeneratedRecipe.collectAsState()

    val chatMessages by viewModel.foodieChatMessages.collectAsState()
    val isFoodieThinking by viewModel.isFoodieThinking.collectAsState()
    val ollamaModel by viewModel.ollamaModelName.collectAsState()
    val ollamaHost by viewModel.ollamaHostUrl.collectAsState()

    var selectedTab by remember { mutableIntStateOf(0) } // 0 = Ask Mr. Foodie, 1 = Custom Recipe Maker
    var chatInputText by remember { mutableStateOf("") }
    var customRecipePrompt by remember { mutableStateOf("") }
    var selectedPreset by remember { mutableStateOf("") }
    var showHostDialog by remember { mutableStateOf(false) }
    var newHostInput by remember { mutableStateOf(ollamaHost) }

    // Floating Mascot Micro-Animation
    val infiniteTransition = rememberInfiniteTransition(label = "robot_mascot_anim")
    val floatOffset by infiniteTransition.animateFloat(
        initialValue = -6f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "mascot_float"
    )
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "mascot_glow"
    )

    // Trigger greeting upon opening
    LaunchedEffect(userName) {
        viewModel.initFoodieGreeting(userName)
    }

    val quickChatPrompts = listOf(
        "How do I make chocolate lava cake? 🍫",
        "Substitute for heavy cream? 🥛",
        "15-minute Japanese Ramen 🍜",
        "Perfect crust on steak 🥩",
        "High protein dinner idea 💪",
        "What can I cook with potatoes & cheese? 🥔"
    )

    val recipePresets = listOf(
        "Make it spicy 🌶️",
        "Quick under 20 mins ⚡",
        "Kid friendly & mild 👶",
        "High protein post-workout 💪",
        "Comforting one-pot dish 🍲",
        "Low carb / Keto 🥑"
    )

    // Host Settings Dialog (Supports real Android devices pointing to machine IP)
    if (showHostDialog) {
        AlertDialog(
            onDismissRequest = { showHostDialog = false },
            title = {
                Text(
                    text = "Ollama Host Settings",
                    fontWeight = FontWeight.Bold,
                    color = IvoryTextPrimary
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Specify your Ollama server address. Use 'http://10.0.2.2:11434' for emulator or your computer's local IP (e.g. 'http://192.168.1.15:11434') for physical phones.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MutedSlateText
                    )
                    OutlinedTextField(
                        value = newHostInput,
                        onValueChange = { newHostInput = it },
                        label = { Text("Host URL") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Current Model: $ollamaModel",
                        style = MaterialTheme.typography.labelSmall,
                        color = AmberGoldPrimary
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.updateOllamaHost(newHostInput)
                        showHostDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AmberGoldPrimary)
                ) {
                    Text("Save & Connect")
                }
            },
            dismissButton = {
                TextButton(onClick = { showHostDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.img_mr_foodie_robot),
                            contentDescription = "Mr. Foodie Robot",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .border(1.5.dp, AmberGoldPrimary, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Ask Me",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = AmberGoldPrimary
                            )
                            Text(
                                text = "Mr. Foodie AI Chef",
                                style = MaterialTheme.typography.labelSmall,
                                color = MutedSlateText
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showHostDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Ollama Settings",
                            tint = MutedSlateText
                        )
                    }
                    if (chatMessages.size > 1) {
                        IconButton(onClick = { viewModel.clearFoodieChat(userName) }) {
                            Icon(
                                imageVector = Icons.Default.DeleteSweep,
                                contentDescription = "Clear Chat",
                                tint = MutedSlateText
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ObsidianBlack
                )
            )
        },
        containerColor = ObsidianBlack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("ai_generator_screen")
        ) {
            // HERO SECTION: Customized Animated Mascot Card
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = DarkCardSurface),
                border = BorderStroke(1.dp, AmberGoldPrimary.copy(alpha = glowAlpha * 0.7f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable {
                        // Tapping mascot triggers friendly greeting voice/action
                        viewModel.speakInstruction("Hi! my name is Mr. Foodie. How may i assist you $userName")
                    }
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Robot Mascot with floating translation & warm gold aura
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .offset(y = floatOffset.dp)
                            .size(150.dp)
                            .clip(RoundedCornerShape(22.dp))
                            .border(2.dp, AmberGoldPrimary.copy(alpha = glowAlpha), RoundedCornerShape(22.dp))
                            .shadow(12.dp, RoundedCornerShape(22.dp), ambientColor = AmberGoldPrimary, spotColor = AmberGoldPrimary)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_mr_foodie_robot),
                            contentDescription = "Mr. Foodie Mascot",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Speech bubble greeting with user's name
                    Surface(
                        color = AmberGoldPrimary.copy(alpha = 0.14f),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, AmberGoldPrimary.copy(alpha = 0.5f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                        ) {
                            Text(text = "💬", fontSize = 18.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Hi ! my name is Mr. Foodie . How may i assist you $userName",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = IvoryTextPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Model status indicator
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = DarkPillBg,
                        border = BorderStroke(1.dp, DarkCardBorder)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "🧠 Powered by Ollama ($ollamaModel)",
                                style = MaterialTheme.typography.labelSmall,
                                color = AmberGoldPrimary,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            // Tab Navigation: Mode Switcher
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = DarkCardSurface,
                contentColor = AmberGoldPrimary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = AmberGoldPrimary
                    )
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = {
                        Text(
                            text = "Ask Anything",
                            fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == 0) AmberGoldPrimary else MutedSlateText
                        )
                    }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = {
                        Text(
                            text = "Custom Recipe Maker",
                            fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == 1) AmberGoldPrimary else MutedSlateText
                        )
                    }
                )
            }

            // TAB 0: FREEFORM OLLAMA Q&A CHAT (ASK ANYTHING OUTSIDE THE LIST)
            if (selectedTab == 0) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(top = 10.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Quick suggested prompts
                    item {
                        Column {
                            Text(
                                text = "Suggested Questions:",
                                style = MaterialTheme.typography.labelMedium,
                                color = MutedSlateText
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                quickChatPrompts.forEach { prompt ->
                                    Surface(
                                        color = DarkPillBg,
                                        shape = RoundedCornerShape(12.dp),
                                        border = BorderStroke(1.dp, DarkCardBorder),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(12.dp))
                                            .clickable {
                                                viewModel.askMrFoodie(prompt)
                                            }
                                    ) {
                                        Text(
                                            text = prompt,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = IvoryTextPrimary,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Chat messages
                    items(chatMessages, key = { it.id }) { msg ->
                        if (msg.isFromUser) {
                            // User Message Bubble (Right aligned)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Surface(
                                    color = AmberGoldPrimary,
                                    shape = RoundedCornerShape(18.dp, 4.dp, 18.dp, 18.dp),
                                    modifier = Modifier.fillMaxWidth(0.85f)
                                ) {
                                    Text(
                                        text = msg.text,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = AmberGoldOnPrimary,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(14.dp)
                                    )
                                }
                            }
                        } else {
                            // Mr. Foodie Message Bubble (Left aligned with mascot icon)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.Top
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.img_mr_foodie_robot),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .border(1.dp, AmberGoldPrimary, CircleShape)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Surface(
                                    color = DarkCardElevated,
                                    shape = RoundedCornerShape(4.dp, 18.dp, 18.dp, 18.dp),
                                    border = BorderStroke(1.dp, DarkCardBorder),
                                    modifier = Modifier.fillMaxWidth(0.92f)
                                ) {
                                    Text(
                                        text = msg.text,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = IvoryTextPrimary,
                                        modifier = Modifier.padding(14.dp)
                                    )
                                }
                            }
                        }
                    }

                    // Thinking indicator
                    if (isFoodieThinking) {
                        item {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(vertical = 4.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.img_mr_foodie_robot),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Surface(
                                    color = DarkCardElevated,
                                    shape = RoundedCornerShape(14.dp),
                                    border = BorderStroke(1.dp, DarkCardBorder)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(16.dp),
                                            color = AmberGoldPrimary,
                                            strokeWidth = 2.dp
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Mr. Foodie is thinking with Ollama...",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MutedSlateText
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Chat Input Bar
                Surface(
                    color = DarkCardSurface,
                    border = BorderStroke(1.dp, DarkCardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        OutlinedTextField(
                            value = chatInputText,
                            onValueChange = { chatInputText = it },
                            placeholder = { Text("Ask Mr. Foodie any recipe or tip...", color = MutedSlateText) },
                            singleLine = true,
                            shape = RoundedCornerShape(20.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = AmberGoldPrimary,
                                unfocusedBorderColor = DarkCardBorder,
                                focusedTextColor = IvoryTextPrimary,
                                unfocusedTextColor = IvoryTextPrimary,
                                focusedContainerColor = DarkPillBg,
                                unfocusedContainerColor = DarkPillBg
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("ai_chat_input")
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = {
                                if (chatInputText.isNotBlank()) {
                                    viewModel.askMrFoodie(chatInputText)
                                    chatInputText = ""
                                }
                            },
                            enabled = !isFoodieThinking && chatInputText.isNotBlank(),
                            modifier = Modifier
                                .background(
                                    if (chatInputText.isNotBlank()) AmberGoldPrimary else DarkPillBg,
                                    CircleShape
                                )
                                .testTag("ai_chat_send_btn")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = "Send",
                                tint = if (chatInputText.isNotBlank()) AmberGoldOnPrimary else MutedSlateText
                            )
                        }
                    }
                }
            }

            // TAB 1: CUSTOM DISH GENERATION RECIPE MAKER
            if (selectedTab == 1) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(top = 12.dp, bottom = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Ingredients in use
                    item {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = DarkCardSurface),
                            border = BorderStroke(1.dp, DarkCardBorder),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Selected Ingredients (${selectedIngredients.size})",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = IvoryTextPrimary
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                if (selectedIngredients.isEmpty()) {
                                    Text(
                                        text = "No ingredients selected. Mr. Foodie will craft a dish using kitchen staples (Chicken, Rice, Garlic, Tomato) or your custom prompt!",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MutedSlateText
                                    )
                                } else {
                                    FlowRow(
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        verticalArrangement = Arrangement.spacedBy(6.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        selectedIngredients.forEach { ing ->
                                            IngredientChip(name = ing)
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Presets and Custom Prompt
                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Chef Preferences (Optional)",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = IvoryTextPrimary
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                recipePresets.forEach { prompt ->
                                    val isSel = selectedPreset == prompt
                                    FilterChip(
                                        selected = isSel,
                                        onClick = {
                                            if (isSel) {
                                                selectedPreset = ""
                                                customRecipePrompt = ""
                                            } else {
                                                selectedPreset = prompt
                                                customRecipePrompt = prompt
                                            }
                                        },
                                        label = { Text(prompt) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = AmberGoldPrimary,
                                            selectedLabelColor = AmberGoldOnPrimary
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            OutlinedTextField(
                                value = customRecipePrompt,
                                onValueChange = { customRecipePrompt = it },
                                placeholder = { Text("e.g. Italian style, extra crispy texture, 20 mins...", color = MutedSlateText) },
                                singleLine = false,
                                maxLines = 3,
                                shape = RoundedCornerShape(14.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = AmberGoldPrimary,
                                    unfocusedBorderColor = DarkCardBorder,
                                    focusedTextColor = IvoryTextPrimary,
                                    unfocusedTextColor = IvoryTextPrimary,
                                    focusedContainerColor = DarkPillBg,
                                    unfocusedContainerColor = DarkPillBg
                                ),
                                modifier = Modifier.fillMaxWidth().testTag("ai_custom_prompt_input")
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {
                                    viewModel.generateAiRecipe(customRecipePrompt)
                                },
                                enabled = !isGenerating,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = AmberGoldPrimary,
                                    contentColor = AmberGoldOnPrimary
                                ),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                                    .testTag("submit_ai_generate_btn")
                            ) {
                                if (isGenerating) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(22.dp),
                                        color = AmberGoldOnPrimary,
                                        strokeWidth = 2.5.dp
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text("Cooking up recipe with Ollama...", fontWeight = FontWeight.Bold)
                                } else {
                                    Text("Generate with Mr. Foodie", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }

                    // Render Generated Recipe
                    generatedRecipe?.let { recipe ->
                        item {
                            Card(
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(containerColor = DarkCardSurface),
                                border = BorderStroke(1.5.dp, AmberGoldPrimary),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(18.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Surface(
                                            color = AmberGoldPrimary,
                                            shape = RoundedCornerShape(8.dp)
                                        ) {
                                            Text(
                                                text = "Mr. Foodie Special",
                                                color = AmberGoldOnPrimary,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }

                                        Text(
                                            text = "${recipe.totalTimeMinutes} min",
                                            fontWeight = FontWeight.Bold,
                                            color = AmberGoldPrimary
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Text(
                                        text = recipe.title,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = IvoryTextPrimary
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = recipe.description,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MutedSlateText
                                    )

                                    Spacer(modifier = Modifier.height(14.dp))

                                    Text(
                                        text = "Ingredients (${recipe.ingredients.size}):",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = IvoryTextPrimary
                                    )
                                    recipe.ingredients.forEach { ing ->
                                        Text(
                                            text = "• ${ing.amount} ${ing.unit} ${ing.name}",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = IvoryTextPrimary,
                                            modifier = Modifier.padding(vertical = 1.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Button(
                                            onClick = {
                                                viewModel.recipeRepository.addCustomAiRecipe(recipe)
                                                viewModel.toggleFavorite(recipe.id)
                                                scope.launch {
                                                    snackbarHostState.showSnackbar("Saved to favorites! ❤️")
                                                }
                                            },
                                            colors = ButtonDefaults.buttonColors(containerColor = DarkPillBg),
                                            shape = RoundedCornerShape(12.dp),
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Icon(imageVector = Icons.Default.Favorite, contentDescription = null, tint = AmberGoldPrimary)
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("Save", color = IvoryTextPrimary)
                                        }

                                        Button(
                                            onClick = {
                                                viewModel.recipeRepository.addCustomAiRecipe(recipe)
                                                onNavigateToDetail(recipe.id)
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = AmberGoldPrimary,
                                                contentColor = AmberGoldOnPrimary
                                            ),
                                            shape = RoundedCornerShape(12.dp),
                                            modifier = Modifier.weight(1.3f)
                                        ) {
                                            Text("View & Cook", fontWeight = FontWeight.Bold)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
