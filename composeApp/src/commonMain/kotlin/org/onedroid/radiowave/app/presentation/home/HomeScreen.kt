package org.onedroid.radiowave.app.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.onedroid.radiowave.app.presentation.home.components.ErrorMsgView
import org.onedroid.radiowave.app.presentation.home.components.Feed
import org.onedroid.radiowave.app.presentation.home.components.FeedTitle
import org.onedroid.radiowave.app.presentation.home.components.HomeTopAppBar
import org.onedroid.radiowave.app.presentation.home.components.RadioGridItem
import org.onedroid.radiowave.app.presentation.home.components.RadioHorizontalGridItem
import org.onedroid.radiowave.app.presentation.home.components.RadioSearchResult
import org.onedroid.radiowave.app.presentation.home.components.ShimmerEffect
import org.onedroid.radiowave.app.presentation.home.components.row
import org.onedroid.radiowave.app.presentation.home.components.single
import org.onedroid.radiowave.app.presentation.home.components.title
import org.onedroid.radiowave.core.theme.compactFeedWidth
import org.onedroid.radiowave.core.theme.extraSmall
import radiowave.composeapp.generated.resources.Res
import radiowave.composeapp.generated.resources.recently_updated

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    rootNavController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val gridState = rememberLazyGridState()
    val radiosSize by mutableStateOf(viewModel.radios.size)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopAppBar(
                rootNavController = rootNavController,
                isSearchActive = viewModel.isSearchActive,
                toggleSearch = viewModel::toggleSearch,
                searchQuery = viewModel.searchQuery,
                updateSearchQuery = viewModel::updateSearchQuery,
                scrollBehavior = scrollBehavior,
                searchResultContent = {
                    RadioSearchResult(
                        isSearchLoading = viewModel.isSearchLoading,
                        searchErrorMsg = viewModel.searchErrorMsg,
                        searchResult = viewModel.searchResult,
                        onRadioClick = {
                            viewModel.toggleSearch()
                        }
                    )
                }
            )
        }
    ) { contentPadding ->
        Feed(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Adaptive(compactFeedWidth),
            state = gridState,
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            title(contentType = "verified-title") {
                FeedTitle(
                    title = "Saved Radios",
                    onClick = { }
                )
            }

            row(contentType = "verified-shimmer-effect") {
                if (viewModel.isLoading) {
                    ShimmerEffect.RadioHorizontalGridItemShimmerEffect()
                } else if (viewModel.errorMsg != null) {
                    ErrorMsgView(
                        errorMsg = viewModel.errorMsg!!,
                        onRetryClick = {

                        }
                    )
                } else {
                    RadioHorizontalGridItem(
                        radios = viewModel.radios,
                    )
                }
            }

            title(contentType = "latest-title") { FeedTitle(title = stringResource(Res.string.recently_updated)) }

            items(
                count = radiosSize,
                key = { index -> viewModel.radios[index].id }
            ) { index ->
                val radio = viewModel.radios[index]
                RadioGridItem(
                    radio = radio,
                    onClick = {

                    }
                )
            }

            if (viewModel.errorMsg != null) {
                single {
                    ErrorMsgView(
                        errorMsg = viewModel.errorMsg!!,
                        onRetryClick = {

                        }
                    )
                }
            } else if (viewModel.isLoading) {
                single {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(extraSmall)
                        )
                    }
                }
            }
        }
    }
}