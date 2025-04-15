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
        radio: Radio,
        isSaved: Boolean,
        onSaveClick: () -> Unit,
        onWebpageClick: () -> Unit,
        onShareClick: () -> Unit
    ) {
        playerController.PLayerUI(
            radio = radio,
            isSaved = isSaved,
            onSaveClick = onSaveClick,
            onWebpageClick = onWebpageClick,
            onShareClick = onShareClick
        )
    }

    override fun onCleared() {
        playerController.onCleared()
    }
}