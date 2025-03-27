package org.onedroid.radiowave.app

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.onedroid.radiowave.app.navigation.NavigationScreenRoot
import org.onedroid.radiowave.app.theme.AppTheme
import org.onedroid.radiowave.app.utils.Theme

@Composable
@Preview
fun App() {
    KoinContext {
        AppTheme(Theme.LIGHT_MODE.name) {
            NavigationScreenRoot()
        }
    }
}