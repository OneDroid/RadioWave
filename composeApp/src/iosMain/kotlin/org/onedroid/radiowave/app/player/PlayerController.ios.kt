package org.onedroid.radiowave.app.player

import androidx.compose.runtime.Composable

actual class PlayerController() : PlayerRepository {
    override fun play(audioUrl: String) {
        TODO("Not yet implemented")
    }

    override fun pauseResume() {
        TODO("Not yet implemented")
    }

    override suspend fun volumeUp() {
        TODO("Not yet implemented")
    }

    override suspend fun volumeDown() {
        TODO("Not yet implemented")
    }

    @Composable
    override fun PlayerStatusIndicator() {
        TODO("Not yet implemented")
    }

    @Composable
    override fun NowPlayingIndicator() {
        TODO("Not yet implemented")
    }
}