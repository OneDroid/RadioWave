package org.onedroid.radiowave.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.onedroid.radiowave.app.theme.Shapes
import org.onedroid.radiowave.app.theme.compactFeedWidth
import org.onedroid.radiowave.app.theme.extraSmall
import org.onedroid.radiowave.app.theme.medium
import org.onedroid.radiowave.app.theme.small
import org.onedroid.radiowave.presentation.home.components.ErrorMsgView
import org.onedroid.radiowave.presentation.home.components.Feed
import org.onedroid.radiowave.presentation.home.components.FeedTitle
import org.onedroid.radiowave.presentation.home.components.FullScreenDialog
import org.onedroid.radiowave.presentation.home.components.HomeTopAppBar
import org.onedroid.radiowave.presentation.home.components.RadioGridItem
import org.onedroid.radiowave.presentation.home.components.RadioHorizontalGridItem
import org.onedroid.radiowave.presentation.home.components.RadioSearchResult
import org.onedroid.radiowave.presentation.home.components.ShimmerEffect
import org.onedroid.radiowave.presentation.home.components.row
import org.onedroid.radiowave.presentation.home.components.single
import org.onedroid.radiowave.presentation.home.components.title
import radiowave.composeapp.generated.resources.Res
import radiowave.composeapp.generated.resources.about_app
import radiowave.composeapp.generated.resources.app_name
import radiowave.composeapp.generated.resources.contact_email
import radiowave.composeapp.generated.resources.contact_label
import radiowave.composeapp.generated.resources.developer
import radiowave.composeapp.generated.resources.github_label
import radiowave.composeapp.generated.resources.github_url
import radiowave.composeapp.generated.resources.ic_launcher
import radiowave.composeapp.generated.resources.last_updated_date
import radiowave.composeapp.generated.resources.last_updated_label
import radiowave.composeapp.generated.resources.license_label
import radiowave.composeapp.generated.resources.license_type
import radiowave.composeapp.generated.resources.maintainer
import radiowave.composeapp.generated.resources.recently_updated
import radiowave.composeapp.generated.resources.version_label
import radiowave.composeapp.generated.resources.version_number
import radiowave.composeapp.generated.resources.webpage_label
import radiowave.composeapp.generated.resources.webpage_url

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
                viewModel.playerRepository.PLayerUI(radio = it)
            }
        },
        sheetPeekHeight = if (viewModel.selectedRadio != null) BottomSheetDefaults.SheetPeekHeight else 0.dp,
        topBar = {
            HomeTopAppBar(
                rootNavController = rootNavController,
                isSearchActive = viewModel.isSearchActive,
                onAboutClick = viewModel::toggleAboutDialog,
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
                            scope.launch { delay(1000) }
                            viewModel.selectedRadio(it)
                            scope.launch { bottomSheetState.bottomSheetState.expand() }
                            viewModel.play(it.url)
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
                            viewModel.getRadios()
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
                        viewModel.play(radio.url)
                    }
                )
            }

            if (viewModel.errorMsg != null) {
                single {
                    ErrorMsgView(
                        errorMsg = viewModel.errorMsg!!,
                        onRetryClick = {
                            viewModel.getRadios()
                            scope.launch {
                                delay(1000)
                            }
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

    if (viewModel.isAboutDialogShowing) {
        ShowAboutDialog(
            onDismiss = { viewModel.toggleAboutDialog() }
        )
    }

}


@Composable
private fun ShowAboutDialog(
    onDismiss: () -> Unit,
) {
    FullScreenDialog(
        onDismissRequest = { onDismiss() },
        title = "About",
        modifier = Modifier.padding(16.dp).clip(Shapes.medium),
        actions = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { onDismiss() }) {
                    Text("Close")
                }
            }
        },
        content = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Image(
                    modifier = Modifier.size(100.dp).padding(bottom = medium),
                    painter = painterResource(Res.drawable.ic_launcher),
                    contentDescription = "App Icon"
                )
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = stringResource(Res.string.app_name)
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(Res.string.developer)
                )
                Text(
                    modifier = Modifier.padding(bottom = small),
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(Res.string.maintainer)
                )
                Spacer(
                    modifier = Modifier.height(2.dp).fillMaxWidth(0.8f)
                        .background(MaterialTheme.colorScheme.primary)
                )
                Text(
                    modifier = Modifier.padding(vertical = medium),
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(Res.string.about_app)
                )

                Text(
                    style = MaterialTheme.typography.labelLarge,
                    text = stringResource(Res.string.last_updated_label)
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(Res.string.last_updated_date)
                )

                Text(
                    modifier = Modifier.padding(top = medium),
                    style = MaterialTheme.typography.labelLarge,
                    text = stringResource(Res.string.contact_label)
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(Res.string.contact_email)
                )

                Text(
                    modifier = Modifier.padding(top = medium),
                    style = MaterialTheme.typography.labelLarge,
                    text = stringResource(Res.string.webpage_label)
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(Res.string.webpage_url)
                )

                Text(
                    modifier = Modifier.padding(top = medium),
                    style = MaterialTheme.typography.labelLarge,
                    text = stringResource(Res.string.github_label)
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(Res.string.github_url)
                )

                Text(
                    modifier = Modifier.padding(top = medium),
                    style = MaterialTheme.typography.labelLarge,
                    text = stringResource(Res.string.license_label)
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(Res.string.license_type)
                )

                Text(
                    modifier = Modifier.padding(top = medium),
                    style = MaterialTheme.typography.labelLarge,
                    text = stringResource(Res.string.version_label)
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(Res.string.version_number)
                )
            }
        }
    )
}