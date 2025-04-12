package org.onedroid.radiowave.app.player

import androidx.compose.runtime.Composable
import org.onedroid.radiowave.domain.Radio

class PlayerRepositoryImpl(
    private val playerController: PlayerController
) : PlayerRepository {
    override fun play(audioUrl: String) {
        playerController.play(audioUrl)
    }
    @Composable
    override fun PLayerUI(
        radio: Radio
    ) {
        playerController.PLayerUI(
            radio = radio
        )
    }

    override fun onCleared() {
        playerController.onCleared()
    }
}