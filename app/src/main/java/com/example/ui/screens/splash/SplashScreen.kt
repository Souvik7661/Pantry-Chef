package com.example.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.AmberGoldPrimary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val SplashDarkBrown = Color(0xFF3D1F0E)
private val SplashWarmTan = Color(0xFFC9AA8D)
private val SplashBarTrack = Color(0xFF221107)

@Composable
fun SplashScreen(
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val progress = remember { Animatable(0f) }
    val logoScale = remember { Animatable(0.82f) }
    val logoAlpha = remember { Animatable(0f) }
    val contentAlpha = remember { Animatable(0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "splash_ambient")

    // Subtle gentle ambient glow breathing
    val ambientPulse by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ambient_pulse"
    )

    // Animated dots for "Loading..."
    val dotCycle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 3.99f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "loading_dots"
    )
    val dotCount = dotCycle.toInt()
    val dots = ".".repeat(dotCount)

    // Run 2.5s loading cycle & animations
    LaunchedEffect(Unit) {
        // Logo entrance animation (fade and spring scale)
        launch {
            logoAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
            )
        }
        launch {
            logoScale.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = 0.62f,
                    stiffness = Spring.StiffnessLow
                )
            )
        }

        // Delay slightly for content / loading bar fade-in
        launch {
            delay(250)
            contentAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 450, easing = FastOutSlowInEasing)
            )
        }

        // Animate loading progress from 0 to 100% over exactly 2.5 seconds (2500ms)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 2500,
                easing = LinearEasing
            )
        )

        // Short smooth pause at 100% before transition to main screen
        delay(120)
        onTimeout()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SplashDarkBrown)
            .systemBarsPadding()
            .testTag("splash_screen")
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                // Allow user tap to quickly enter if desired
                onTimeout()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            // Main Branding Logo Container with Ambient Glow
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(310.dp)
                    .graphicsLayer {
                        scaleX = logoScale.value
                        scaleY = logoScale.value
                        alpha = logoAlpha.value
                    }
                    .testTag("splash_logo")
            ) {
                // Ambient warm halo behind the logo
                Box(
                    modifier = Modifier
                        .size(240.dp)
                        .graphicsLayer {
                            scaleX = ambientPulse
                            scaleY = ambientPulse
                            alpha = 0.22f
                        }
                        .background(
                            Brush.radialGradient(
                                listOf(
                                    AmberGoldPrimary,
                                    SplashWarmTan.copy(alpha = 0.4f),
                                    Color.Transparent
                                )
                            ),
                            shape = CircleShape
                        )
                )

                // The Brand Image (Recipes IN MINUTES)
                Image(
                    painter = painterResource(id = R.drawable.img_recipes_logo),
                    contentDescription = "Recipes in Minutes Brand Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(32.dp))
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Loading Section (Animated Loading bar & 0-100% progress)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer { alpha = contentAlpha.value }
            ) {
                // "Loading" Header Row with live percentage
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width(260.dp)
                        .padding(horizontal = 4.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Loading$dots",
                        color = SplashWarmTan,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 1.2.sp,
                        modifier = Modifier.testTag("loading_text")
                    )

                    val percentage = (progress.value * 100).toInt().coerceIn(0, 100)
                    Text(
                        text = "$percentage%",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 0.8.sp,
                        modifier = Modifier.testTag("loading_percentage")
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Modern Pill Loading Progress Bar (0 to 100%)
                Box(
                    modifier = Modifier
                        .width(260.dp)
                        .height(10.dp)
                        .clip(RoundedCornerShape(50))
                        .background(SplashBarTrack)
                        .border(
                            width = 1.dp,
                            color = SplashWarmTan.copy(alpha = 0.35f),
                            shape = RoundedCornerShape(50)
                        )
                        .testTag("loading_bar")
                ) {
                    // Active Animated Progress Fill
                    val currentFraction = progress.value.coerceIn(0f, 1f)
                    if (currentFraction > 0.005f) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(currentFraction)
                                .height(10.dp)
                                .clip(RoundedCornerShape(50))
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(
                                            SplashWarmTan,
                                            AmberGoldPrimary,
                                            Color.White
                                        )
                                    )
                                )
                                .shadow(elevation = 4.dp, shape = RoundedCornerShape(50))
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Subtle Gourmet Tagline
                Text(
                    text = "Gourmet Meals from Your Kitchen",
                    color = SplashWarmTan.copy(alpha = 0.65f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.6.sp
                )
            }
        }
    }
}
