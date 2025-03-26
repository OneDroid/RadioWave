package org.onedroid.radiowave.app.presentation.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import org.onedroid.radiowave.app.presentation.home.components.HomeTopAppBar

@Composable
fun HomeScreen(
    rootNavController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            HomeTopAppBar(
                rootNavController = rootNavController,
                isSearchActive = viewModel.isSearchActive,
                toggleSearch = viewModel::toggleSearch,
                searchQuery = viewModel.searchQuery,
                updateSearchQuery = viewModel::updateSearchQuery
            )
        }
    ) { contentPadding ->

    }
}