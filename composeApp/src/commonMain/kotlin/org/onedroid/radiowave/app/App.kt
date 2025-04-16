package org.onedroid.radiowave.app

import androidx.compose.runtime.Composable
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.onedroid.radiowave.app.navigation.NavigationScreenRoot
import org.onedroid.radiowave.app.theme.AppTheme
import org.onedroid.radiowave.presentation.settings.SettingViewModel

@Composable
fun App() {
    KoinContext {
        val settingViewModel = koinViewModel<SettingViewModel>()
        val currentTheme = settingViewModel.theme
        AppTheme(currentTheme) {
            NavigationScreenRoot(
                settingViewModel = settingViewModel
            )
        }
    }
}