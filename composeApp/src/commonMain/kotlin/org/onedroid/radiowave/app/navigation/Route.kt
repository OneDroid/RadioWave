package org.onedroid.radiowave.app.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Home : Route

    @Serializable
    data object Settings : Route
}