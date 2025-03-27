package org.onedroid.radiowave.app.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.stringResource
import org.onedroid.radiowave.app.domain.Radio
import org.onedroid.radiowave.core.utils.UiText
import radiowave.composeapp.generated.resources.Res
import radiowave.composeapp.generated.resources.no_search_result

@Composable
fun RadioSearchResult(
    isSearchLoading: Boolean,
    searchErrorMsg: UiText? = null,
    searchResult: List<Radio>,
    onRadioClick: (Radio) -> Unit
) {
    val searchResultListState = rememberLazyListState()
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (isSearchLoading) {
                    CircularProgressIndicator()
                } else {
                    when {
                        searchErrorMsg != null -> {
                            Text(
                                text = searchErrorMsg.asString(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        searchResult.isEmpty() -> {
                            Text(
                                text = stringResource(Res.string.no_search_result),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }

                        else -> {
                            RadioList(
                                radios = searchResult,
                                onRadioClick = { radio ->
                                    onRadioClick(radio)
                                },
                                modifier = Modifier.fillMaxSize(),
                                scrollState = searchResultListState
                            )
                        }
                    }
                }
            }
        }
    }
}