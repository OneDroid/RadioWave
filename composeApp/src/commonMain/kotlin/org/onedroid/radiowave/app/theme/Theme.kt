package org.onedroid.radiowave.app.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.onedroid.radiowave.app.utils.Theme

@Composable
fun AppTheme(
    appTheme: String?,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when (appTheme) {
        Theme.LIGHT_MODE.name -> {
            LightColorScheme
        }

        Theme.DARK_MODE.name -> {
            DarkColorScheme
        }

        else -> {
            if (darkTheme) {
                DarkColorScheme
            } else {
                LightColorScheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography(),
        content = content,
    )
}