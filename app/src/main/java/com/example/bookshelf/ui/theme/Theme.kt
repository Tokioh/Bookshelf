package com.example.bookshelf.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = BookshelfNavyDark,
    onPrimary = Color(0xFF0F172A),
    primaryContainer = BookshelfContainerDark,
    onPrimaryContainer = Color(0xFFF8FAFC),
    secondary = BookshelfAmberDark,
    background = BookshelfBackgroundDark,
    surface = BookshelfSurfaceDark,
    onBackground = Color(0xFFF8FAFC),
    onSurface = Color(0xFFF8FAFC)
)

private val LightColorScheme = lightColorScheme(
    primary = BookshelfNavy,
    onPrimary = Color.White,
    primaryContainer = BookshelfContainerLight,
    onPrimaryContainer = BookshelfNavy,
    secondary = BookshelfAmber,
    onSecondary = Color.White,
    secondaryContainer = BookshelfAmberContainer,
    background = BookshelfBackground,
    surface = BookshelfSurface,
    onBackground = Color(0xFF0F172A),
    onSurface = Color(0xFF0F172A)
)

@Composable
fun BookshelfTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
