package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = AmberGoldPrimary,
    onPrimary = AmberGoldOnPrimary,
    primaryContainer = AmberGoldContainer,
    onPrimaryContainer = AmberGoldOnContainer,
    secondary = HerbGreen,
    onSecondary = HerbGreenOn,
    secondaryContainer = HerbGreenContainer,
    onSecondaryContainer = HerbGreenOnContainer,
    tertiary = HoneySaffron,
    background = ObsidianBlack,
    surface = DarkCardSurface,
    surfaceVariant = DarkCardElevated,
    onBackground = IvoryTextPrimary,
    onSurface = IvoryTextPrimary,
    onSurfaceVariant = MutedSlateText,
    outline = DarkCardBorder
)

private val LightColorScheme = DarkColorScheme // Default to dark luxury theme as shown in the requested UI

@Composable
fun PantryChefTheme(
    darkTheme: Boolean = true,
    // Keep bespoke culinary dark aesthetics consistent without OS dynamic wallpaper interference
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            dynamicDarkColorScheme(context)
        }
        else -> DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
