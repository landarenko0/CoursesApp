package com.example.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    surfaceContainerHighest = surfaceContainerHighest,
    onSurfaceVariant = onSurfaceVariant,
    onSurface = onSurface,
    background = background
)

//private val LightColorScheme = lightColorScheme(
//    primary = primary,
//    onPrimary = onPrimary,
//    onSecondary = onSecondary,
//    onBackground = onBackground
//)

@Composable
fun CoursesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}