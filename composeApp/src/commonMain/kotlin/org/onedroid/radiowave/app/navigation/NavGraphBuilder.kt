package org.onedroid.radiowave.app.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.onedroid.radiowave.app.presentation.home.HomeScreen

fun NavGraphBuilder.navGraphBuilder(
    rootNavController: NavController
) {
    composable<Route.Home> {
        HomeScreen(
            rootNavController = rootNavController
        )
    }

    composable<Route.Settings> {
        Text(text = "Settings")
    }
}