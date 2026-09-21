package com.example.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Kitchen
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ui.navigation.Screen
import com.example.ui.screens.ai.AiGeneratorScreen
import com.example.ui.screens.cooking.CookingModeScreen
import com.example.ui.screens.detail.RecipeDetailScreen
import com.example.ui.screens.discover.DiscoverScreen
import com.example.ui.screens.home.HomeScreen
import com.example.ui.screens.login.LoginScreen
import com.example.ui.screens.mealplan.MealPlanScreen
import com.example.ui.screens.onboarding.OnboardingScreen
import com.example.ui.screens.pantry.PantryScreen
import com.example.ui.screens.profile.ProfileScreen
import com.example.ui.screens.results.RecipeResultsScreen
import com.example.ui.screens.shopping.ShoppingListScreen
import com.example.ui.screens.splash.SplashScreen
import com.example.ui.theme.AmberGoldPrimary
import com.example.ui.theme.MutedSlateText
import com.example.ui.theme.TerracottaPrimary
import com.example.viewmodel.MainViewModel
import kotlinx.coroutines.launch

data class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun MainApp(viewModel: MainViewModel) {
    val context = LocalContext.current
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val userPrefs by viewModel.userPreferences.collectAsState()
    val updateInfo by viewModel.updateInfo.collectAsState()

    // Remote App Update Dialog
    if (updateInfo.hasUpdate) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissUpdateDialog() },
            icon = {
                Surface(
                    shape = CircleShape,
                    color = TerracottaPrimary.copy(alpha = 0.15f),
                    modifier = Modifier.size(52.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.SystemUpdate,
                            contentDescription = null,
                            tint = TerracottaPrimary,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            },
            title = {
                Text(
                    text = updateInfo.releaseTitle.ifEmpty { "Update Available! 🚀" },
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "A new version (v${updateInfo.latestVersionName}) is available with new recipes and features.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (updateInfo.releaseNotes.isNotEmpty()) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                        ) {
                            Text(
                                text = updateInfo.releaseNotes,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(updateInfo.downloadUrl)).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        context.startActivity(intent)
                        viewModel.dismissUpdateDialog()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary)
                ) {
                    Text("Update Now ⬇️")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.dismissUpdateDialog() }) {
                    Text("Later")
                }
            }
        )
    }

    val bottomNavItems = remember {
        listOf(
            BottomNavItem(
                route = Screen.Home.route,
                title = "Kitchen",
                selectedIcon = Icons.Filled.Kitchen,
                unselectedIcon = Icons.Outlined.Kitchen
            ),
            BottomNavItem(
                route = Screen.Discover.route,
                title = "Discover",
                selectedIcon = Icons.Filled.Explore,
                unselectedIcon = Icons.Outlined.Explore
            ),
            BottomNavItem(
                route = Screen.MealPlan.route,
                title = "Meal Plan",
                selectedIcon = Icons.Filled.CalendarMonth,
                unselectedIcon = Icons.Outlined.CalendarMonth
            ),
            BottomNavItem(
                route = Screen.Shopping.route,
                title = "Shopping",
                selectedIcon = Icons.Filled.ShoppingCart,
                unselectedIcon = Icons.Outlined.ShoppingCart
            ),
            BottomNavItem(
                route = Screen.Profile.route,
                title = "Profile",
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person
            )
        )
    }

    // Hide bottom bar on onboarding, cooking mode, or detail views
    val shouldShowBottomBar = bottomNavItems.any { it.route == currentRoute }

    val startDestination = Screen.Splash.route

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = shouldShowBottomBar,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp,
                    modifier = Modifier.testTag("bottom_nav_bar")
                ) {
                    bottomNavItems.forEach { item ->
                        val isSelected = currentRoute == item.route
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.title,
                                    modifier = Modifier.size(22.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    fontSize = 11.sp
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = AmberGoldPrimary,
                                selectedTextColor = AmberGoldPrimary,
                                indicatorColor = AmberGoldPrimary.copy(alpha = 0.16f),
                                unselectedIconColor = MutedSlateText,
                                unselectedTextColor = MutedSlateText
                            ),
                            modifier = Modifier.testTag("nav_tab_${item.title.lowercase()}")
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    onTimeout = {
                        val destination = if (viewModel.isLoggedIn.value) Screen.Home.route else Screen.Login.route
                        navController.navigate(destination) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Login.route) {
                LoginScreen(
                    viewModel = viewModel,
                    onLoginSuccess = {
                        val hasCompletedOnboarding = userPrefs?.isOnboarded == true
                        val nextRoute = if (hasCompletedOnboarding) Screen.Home.route else Screen.Onboarding.route
                        navController.navigate(nextRoute) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onContinueAsGuest = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Onboarding.route) {
                OnboardingScreen(
                    onComplete = { name, diet, allergies, skill, time, staples ->
                        viewModel.completeOnboarding(
                            name = name,
                            dietary = diet,
                            allergies = allergies,
                            skill = skill,
                            time = time,
                            starterPantrySelection = staples
                        )
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = viewModel,
                    onNavigateToResults = { navController.navigate(Screen.Results.route) },
                    onNavigateToDetail = { id -> navController.navigate(Screen.RecipeDetail.createRoute(id)) },
                    onNavigateToPantry = { navController.navigate(Screen.Pantry.route) },
                    onNavigateToAiGenerator = { navController.navigate(Screen.AiGenerator.route) }
                )
            }

            composable(Screen.Discover.route) {
                DiscoverScreen(
                    viewModel = viewModel,
                    onNavigateToDetail = { id -> navController.navigate(Screen.RecipeDetail.createRoute(id)) }
                )
            }

            composable(Screen.MealPlan.route) {
                MealPlanScreen(
                    viewModel = viewModel,
                    onNavigateToDetail = { id -> navController.navigate(Screen.RecipeDetail.createRoute(id)) }
                )
            }

            composable(Screen.Shopping.route) {
                ShoppingListScreen(viewModel = viewModel)
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    viewModel = viewModel,
                    onNavigateToDetail = { id -> navController.navigate(Screen.RecipeDetail.createRoute(id)) },
                    onRestartOnboarding = { navController.navigate(Screen.Onboarding.route) },
                    onNavigateToLogin = { navController.navigate(Screen.Login.route) }
                )
            }

            composable(Screen.Results.route) {
                RecipeResultsScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onNavigateToDetail = { id -> navController.navigate(Screen.RecipeDetail.createRoute(id)) }
                )
            }

            composable(Screen.Pantry.route) {
                PantryScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Screen.AiGenerator.route) {
                AiGeneratorScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onNavigateToDetail = { id -> navController.navigate(Screen.RecipeDetail.createRoute(id)) }
                )
            }

            composable(
                route = Screen.RecipeDetail.route,
                arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getString("recipeId") ?: ""
                RecipeDetailScreen(
                    recipeId = recipeId,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onStartCooking = { id -> navController.navigate(Screen.CookingMode.createRoute(id)) }
                )
            }

            composable(
                route = Screen.CookingMode.route,
                arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getString("recipeId") ?: ""
                CookingModeScreen(
                    recipeId = recipeId,
                    viewModel = viewModel,
                    onClose = { navController.popBackStack() }
                )
            }
        }
    }
}
