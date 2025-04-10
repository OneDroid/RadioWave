package org.onedroid.radiowave.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.onedroid.radiowave.app.theme.compactFeedWidth
import org.onedroid.radiowave.app.theme.extraSmall
import org.onedroid.radiowave.presentation.home.components.ErrorMsgView
import org.onedroid.radiowave.presentation.home.components.Feed
import org.onedroid.radiowave.presentation.home.components.FeedTitle
import org.onedroid.radiowave.presentation.home.components.HomeTopAppBar
import org.onedroid.radiowave.presentation.home.components.RadioDetailsBottomSheet
import org.onedroid.radiowave.presentation.home.components.RadioGridItem
import org.onedroid.radiowave.presentation.home.components.RadioHorizontalGridItem
import org.onedroid.radiowave.presentation.home.components.RadioSearchResult
import org.onedroid.radiowave.presentation.home.components.ShimmerEffect
import org.onedroid.radiowave.presentation.home.components.row
import org.onedroid.radiowave.presentation.home.components.single
import org.onedroid.radiowave.presentation.home.components.title
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
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        scaffoldState = bottomSheetState,
        sheetContent = {
            viewModel.selectedRadio?.let {
                RadioDetailsBottomSheet(viewModel.selectedRadio!!)
            }
        },
        sheetPeekHeight = if (viewModel.selectedRadio != null) BottomSheetDefaults.SheetPeekHeight else 0.dp,
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
                if (index == radiosSize - 1 && !viewModel.isLoading && viewModel.errorMsg == null) {
                    viewModel.getRadios()
                }
                RadioGridItem(
                    radio = radio,
                    onClick = {
                        viewModel.selectedRadio(radio)
                        scope.launch { bottomSheetState.bottomSheetState.expand() }
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