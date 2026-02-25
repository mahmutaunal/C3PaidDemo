package com.mahmutalperenunal.c3paiddemo.core.ui.theme

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

private val LightColorScheme = lightColorScheme(
    primary = PurplePrimary,
    secondary = PurpleSecondary,

    background = ScreenBackground,
    surface = CardBackground,

    onPrimary = Color.White,
    onSecondary = Color.White,

    onBackground = TextPrimary,
    onSurface = TextPrimary,

    surfaceVariant = LightPurpleCard,
    onSurfaceVariant = TextPrimary,

    error = ErrorRed,
    onError = Color.White,

    outline = TextSecondary
)

private val DarkColorScheme = darkColorScheme(
    primary = PurplePrimary,
    secondary = PurpleSecondary,

    background = Color(0xFF121212),
    surface = Color(0xFF1B1B1B),

    onPrimary = Color.White,
    onSecondary = Color.White,

    onBackground = Color(0xFFEAEAEA),
    onSurface = Color(0xFFEAEAEA),

    surfaceVariant = Color(0xFF242424),
    onSurfaceVariant = Color(0xFFE0E0E0),

    error = ErrorRed,
    onError = Color.White,

    outline = Color(0xFF8A8A8A)
)

@Composable
fun C3PaidDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}