package com.example.notanotherweatherapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun NotAnotherWeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = ColorScheme(
        primary = Color(0xFF393B41),
        onPrimary = Color.Black,
        primaryContainer = Color(0xFFF5FF78),
        onPrimaryContainer = Color.White,
        inversePrimary = Color(0xFFBB86FC),
        secondary = Color(0xFFF5FF78),
        onSecondary = Color.Black,
        secondaryContainer = Color(0xFF018786),
        onSecondaryContainer = Color.White,
        tertiary = Color(0xFF8C8E93),
        onTertiary = Color.Black,
        tertiaryContainer = Color(0xFF018786),
        onTertiaryContainer = Color.White,
        background = Color(0xFF393B41),
        onBackground = Color.Black,
        surface = Color(0xFF393B41),
        onSurface = Color.Black,
        surfaceVariant = Color(0xFF393B41),
        onSurfaceVariant = Color.Black,
        surfaceTint = Color(0xFFF5FF78),
        inverseSurface = Color(0xFF121212),
        inverseOnSurface = Color.White,
        error = Color(0xFFB00020),
        onError = Color.White,
        errorContainer = Color(0xFFFDE7E7),
        onErrorContainer = Color.Black,
        outline = Color(0xFF737373),
        outlineVariant = Color(0xFFB6B6B6),
        scrim = Color(0x80000000)
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}