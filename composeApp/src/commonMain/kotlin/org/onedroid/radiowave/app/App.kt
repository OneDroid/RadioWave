package org.onedroid.radiowave.app

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.onedroid.radiowave.app.navigation.NavigationScreenRoot
import org.onedroid.radiowave.core.theme.AppTheme
import org.onedroid.radiowave.core.utils.Theme

@Composable
@Preview
fun App() {
    KoinContext {
        AppTheme(Theme.DARK_MODE.name) {
            NavigationScreenRoot()
        }
    }
}