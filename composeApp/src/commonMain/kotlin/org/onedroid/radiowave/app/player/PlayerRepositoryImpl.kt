package org.onedroid.radiowave.app.player

import androidx.compose.runtime.Composable

class PlayerRepositoryImpl(
    private val playerController: PlayerController
) : PlayerRepository {

    override fun play(audioUrl: String) {
        playerController.play(audioUrl)
    }

    override fun pauseResume() {
        playerController.pauseResume()
    }

    override suspend fun volumeUp() {
        playerController.volumeUp()
    }

    override suspend fun volumeDown() {
        playerController.volumeDown()
    }

    @Composable
    override fun PlayerStatusIndicator() {
        playerController.PlayerStatusIndicator()
    }

    @Composable
    override fun NowPlayingIndicator() {
        playerController.NowPlayingIndicator()
    }
}