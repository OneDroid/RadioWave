package org.onedroid.radiowave.app.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationScreenRoot() {
    val rootNavController = rememberNavController()
    Scaffold {
        NavHost(
            navController = rootNavController,
            startDestination = Route.Home,
        ) {
            navGraphBuilder(
                rootNavController = rootNavController
            )
        }
    }
}