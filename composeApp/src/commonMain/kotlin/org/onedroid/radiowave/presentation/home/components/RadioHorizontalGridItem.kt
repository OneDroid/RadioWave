package org.onedroid.radiowave.presentation.home.components

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.launch
import org.onedroid.radiowave.domain.Radio
import org.onedroid.radiowave.app.theme.horizontalGridMaxHeight
import org.onedroid.radiowave.app.theme.horizontalGridMaxWidth
import org.onedroid.radiowave.app.theme.zero

@Composable
fun RadioHorizontalGridItem(
    radios: List<Radio>,
    onRadioClick: (Radio) -> Unit,
    modifier: Modifier = Modifier
) {
    val gridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()
    val uniqueRadios = radios.distinctBy { it.id to it.url }
    val radiosSize by mutableStateOf(uniqueRadios.size)

    LazyHorizontalGrid(
        state = gridState,
        rows = GridCells.Fixed(2),
        modifier = modifier
            .heightIn(max = horizontalGridMaxHeight)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume()
                    coroutineScope.launch {
                        gridState.scrollBy(-dragAmount)
                    }
                }
            },
        verticalArrangement = Arrangement.spacedBy(zero),
        horizontalArrangement = Arrangement.spacedBy(zero)
    ) {
        items(radiosSize, key = { uniqueRadios[it].id }, contentType = { "radios" }) {
            RadioGridItem(
                radio = uniqueRadios[it],
                onClick = {
                    onRadioClick(uniqueRadios[it])
                },
                modifier = Modifier.width(horizontalGridMaxWidth)
            )
        }
    }
}