package org.onedroid.radiowave.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.onedroid.radiowave.presentation.home.HomeScreen
import org.onedroid.radiowave.presentation.settings.SettingScreen
import org.onedroid.radiowave.presentation.settings.SettingViewModel

fun NavGraphBuilder.navGraphBuilder(
    rootNavController: NavController,
    settingViewModel: SettingViewModel
) {
    composable<Route.Home> {
        HomeScreen(
            rootNavController = rootNavController
        )
    }
    composable<Route.Settings> {
        SettingScreen(
            viewModel = settingViewModel,
            rootNavController = rootNavController
        )
    }
}