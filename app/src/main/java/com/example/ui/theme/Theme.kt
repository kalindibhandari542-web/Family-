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
    primary = MintAccent,
    onPrimary = Color.Black,
    primaryContainer = EmeraldGreenDark,
    onPrimaryContainer = Color.White,
    secondary = SaffronGold,
    onSecondary = Color.Black,
    tertiary = WarmGold,
    background = DarkChatBackground,
    surface = SurfaceDark,
    onBackground = Color(0xFFE9EDEF),
    onSurface = Color(0xFFE9EDEF),
    outline = CardBorderDark
)

private val LightColorScheme = lightColorScheme(
    primary = EmeraldGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD3E8E1),
    onPrimaryContainer = EmeraldGreenDark,
    secondary = SaffronGold,
    onSecondary = Color.White,
    tertiary = WarmGold,
    background = LightChatBackground,
    surface = SurfaceLight,
    onBackground = Color(0xFF111B21),
    onSurface = Color(0xFF111B21),
    outline = CardBorderLight
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep distinctive brand color scheme for the chat app
    content: @Composable () -> Unit,
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
