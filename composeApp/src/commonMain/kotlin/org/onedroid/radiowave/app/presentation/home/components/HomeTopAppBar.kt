package org.onedroid.radiowave.app.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.onedroid.radiowave.app.navigation.Route
import radiowave.composeapp.generated.resources.Res
import radiowave.composeapp.generated.resources.app_name

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    rootNavController: NavController,
    searchQuery: String,
    updateSearchQuery: (String) -> Unit,
    isSearchActive: Boolean,
    toggleSearch: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(Res.string.app_name),
                style = TextStyle(
                    fontSize = 20.sp
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Outlined.Info, contentDescription = "Info")
            }
        },
        actions = {
            IconButton(onClick = {
                toggleSearch()
            }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
            IconButton(onClick = {
                rootNavController.navigate(Route.Settings)
            }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        }
    )

    if (isSearchActive) {
        EmbeddedSearchBar(
            query = searchQuery,
            onQueryChange = { query ->
                updateSearchQuery(query)
            },
            onSearch = {
                toggleSearch()
            },
            content = {

            },
            onBack = {
                toggleSearch()
            },
            isActive = true
        )
    }
}