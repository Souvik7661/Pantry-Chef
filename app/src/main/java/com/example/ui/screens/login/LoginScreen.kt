package com.example.ui.screens.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.AmberGoldPrimary
import com.example.ui.theme.DarkCardBorder
import com.example.ui.theme.DarkCardSurface
import com.example.ui.theme.HoneySaffron
import com.example.ui.theme.IvoryTextPrimary
import com.example.ui.theme.MutedSlateText
import com.example.ui.theme.ObsidianBlack
import com.example.viewmodel.MainViewModel

@Composable
fun LoginScreen(
    viewModel: MainViewModel,
    onLoginSuccess: () -> Unit,
    onContinueAsGuest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val authError by viewModel.authError.collectAsState()
    val focusManager = LocalFocusManager.current

    var isSignUpMode by remember { mutableStateOf(false) }
    var nameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }

    val greatVibesFont = remember {
        try {
            FontFamily(Font(R.font.great_vibes))
        } catch (e: Exception) {
            FontFamily.Cursive
        }
    }

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0C0C0F),
            Color(0xFF131318),
            Color(0xFF191820)
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .testTag("login_screen")
    ) {
        // Ambient golden warm halo in the top-center background
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(320.dp)
                .padding(top = 10.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            AmberGoldPrimary.copy(alpha = 0.16f),
                            AmberGoldPrimary.copy(alpha = 0.05f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // 1. BRANDING HEADER
            Surface(
                shape = CircleShape,
                color = Color(0xFF201D1A),
                border = BorderStroke(1.5.dp, AmberGoldPrimary.copy(alpha = 0.5f)),
                shadowElevation = 12.dp,
                modifier = Modifier
                    .size(90.dp)
                    .testTag("login_logo_badge")
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_recipes_logo),
                        contentDescription = "PantryChef Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(76.dp)
                            .clip(CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "PantryChef",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = IvoryTextPrimary,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Recipes in minutes",
                fontFamily = greatVibesFont,
                fontSize = 24.sp,
                color = HoneySaffron,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 2. INPUT FORM FIELDS
            // Optional Name Field for Sign Up mode
            AnimatedVisibility(visible = isSignUpMode) {
                Column {
                    OutlinedTextField(
                        value = nameInput,
                        onValueChange = {
                            nameInput = it
                            if (authError != null) viewModel.clearAuthError()
                        },
                        label = { Text("Chef Name", color = MutedSlateText) },
                        placeholder = { Text("e.g. Chef Alex", color = MutedSlateText.copy(alpha = 0.6f)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = AmberGoldPrimary.copy(alpha = 0.85f)
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(26.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = DarkCardSurface,
                            unfocusedContainerColor = DarkCardSurface,
                            focusedBorderColor = AmberGoldPrimary,
                            unfocusedBorderColor = DarkCardBorder,
                            focusedTextColor = IvoryTextPrimary,
                            unfocusedTextColor = IvoryTextPrimary
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = FocusDirection.Down.let { ImeAction.Next }),
                        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("login_name_input")
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }

            // Email Field
            OutlinedTextField(
                value = emailInput,
                onValueChange = {
                    emailInput = it
                    if (authError != null) viewModel.clearAuthError()
                },
                label = { Text("Email Address", color = MutedSlateText) },
                placeholder = { Text("chef@example.com", color = MutedSlateText.copy(alpha = 0.6f)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = AmberGoldPrimary.copy(alpha = 0.85f)
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(26.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = DarkCardSurface,
                    unfocusedContainerColor = DarkCardSurface,
                    focusedBorderColor = AmberGoldPrimary,
                    unfocusedBorderColor = DarkCardBorder,
                    focusedTextColor = IvoryTextPrimary,
                    unfocusedTextColor = IvoryTextPrimary
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("login_email_input")
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Password Field
            OutlinedTextField(
                value = passwordInput,
                onValueChange = {
                    passwordInput = it
                    if (authError != null) viewModel.clearAuthError()
                },
                label = { Text("Password", color = MutedSlateText) },
                placeholder = { Text("••••••••", color = MutedSlateText.copy(alpha = 0.6f)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = AmberGoldPrimary.copy(alpha = 0.85f)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            tint = MutedSlateText
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true,
                shape = RoundedCornerShape(26.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = DarkCardSurface,
                    unfocusedContainerColor = DarkCardSurface,
                    focusedBorderColor = AmberGoldPrimary,
                    unfocusedBorderColor = DarkCardBorder,
                    focusedTextColor = IvoryTextPrimary,
                    unfocusedTextColor = IvoryTextPrimary
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    if (!isLoading) {
                        isLoading = true
                        if (isSignUpMode) {
                            viewModel.signUpUser(nameInput, emailInput, passwordInput) {
                                isLoading = false
                                onLoginSuccess()
                            }
                        } else {
                            viewModel.loginUser(emailInput, passwordInput, rememberMe) {
                                isLoading = false
                                onLoginSuccess()
                            }
                        }
                    }
                }),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("login_password_input")
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Remember Me & Forgot Password Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        rememberMe = !rememberMe
                    }
                ) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = AmberGoldPrimary,
                            uncheckedColor = MutedSlateText,
                            checkmarkColor = Color(0xFF101012)
                        ),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Remember Me",
                        style = MaterialTheme.typography.bodySmall,
                        color = MutedSlateText
                    )
                }

                if (!isSignUpMode) {
                    Text(
                        text = "Forgot Password?",
                        style = MaterialTheme.typography.bodySmall,
                        color = HoneySaffron,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .clickable {
                                // Clear error and prefill demo prompt
                                viewModel.clearAuthError()
                            }
                            .padding(4.dp)
                    )
                }
            }

            // Error Banner
            AnimatedVisibility(visible = authError != null) {
                authError?.let { err ->
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFF3F1916),
                        border = BorderStroke(1.dp, Color(0xFF8B2E24)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        Text(
                            text = "⚠️ $err",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFFFB4AB),
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // 3. PRIMARY CTA BUTTON (Glowing Amber Gold)
            Button(
                onClick = {
                    focusManager.clearFocus()
                    if (!isLoading) {
                        isLoading = true
                        if (isSignUpMode) {
                            viewModel.signUpUser(nameInput, emailInput, passwordInput) {
                                isLoading = false
                                onLoginSuccess()
                            }
                        } else {
                            viewModel.loginUser(emailInput, passwordInput, rememberMe) {
                                isLoading = false
                                onLoginSuccess()
                            }
                        }
                    }
                },
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AmberGoldPrimary),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .shadow(12.dp, RoundedCornerShape(26.dp), ambientColor = AmberGoldPrimary, spotColor = AmberGoldPrimary)
                    .testTag("login_submit_button")
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color(0xFF1B1204),
                        strokeWidth = 2.5.dp,
                        modifier = Modifier.size(22.dp)
                    )
                } else {
                    Text(
                        text = if (isSignUpMode) "Create Chef Account" else "Sign In to Kitchen",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B1204)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 4. "OR CONTINUE WITH" DIVIDER
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFF2A2A35)
                )
                Text(
                    text = "  Or continue with  ",
                    style = MaterialTheme.typography.bodySmall,
                    color = MutedSlateText
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFF2A2A35)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 5. GOOGLE SIGN IN BUTTON
            Surface(
                shape = RoundedCornerShape(26.dp),
                color = Color(0xFF1A1A22),
                border = BorderStroke(1.dp, Color(0xFF2D2D38)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clickable {
                        viewModel.loginWithGoogle {
                            onLoginSuccess()
                        }
                    }
                    .testTag("google_login_button")
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Stylized Google 'G' Icon
                    Text(
                        text = "G",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        color = AmberGoldPrimary
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Continue with Google",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = IvoryTextPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 6. GUEST ACCESS BUTTON
            TextButton(
                onClick = {
                    viewModel.loginAsGuest {
                        onContinueAsGuest()
                    }
                },
                modifier = Modifier.testTag("login_guest_button")
            ) {
                Text(
                    text = "Explore as Guest 🍳",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MutedSlateText
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 7. FOOTER TOGGLE (Sign In <-> Sign Up)
            val footerText = buildAnnotatedString {
                if (isSignUpMode) {
                    append("Already have an account? ")
                    withStyle(style = SpanStyle(color = AmberGoldPrimary, fontWeight = FontWeight.Bold)) {
                        append("Sign In")
                    }
                } else {
                    append("Don't have an account? ")
                    withStyle(style = SpanStyle(color = AmberGoldPrimary, fontWeight = FontWeight.Bold)) {
                        append("Sign Up")
                    }
                }
            }

            Text(
                text = footerText,
                style = MaterialTheme.typography.bodyMedium,
                color = MutedSlateText,
                modifier = Modifier
                    .clickable {
                        isSignUpMode = !isSignUpMode
                        viewModel.clearAuthError()
                    }
                    .padding(8.dp)
                    .testTag("login_toggle_mode")
            )
        }
    }
}
