package org.onedroid.radiowave.app.player

import androidx.compose.runtime.Composable
import org.onedroid.radiowave.domain.Radio

interface PlayerRepository {
    fun play(audioUrl: String)
    @Composable
    fun PLayerUI(radio: Radio)
    fun onCleared()
}