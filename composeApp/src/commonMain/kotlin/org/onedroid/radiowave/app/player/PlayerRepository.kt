package org.onedroid.radiowave.app.player

import androidx.compose.runtime.Composable

interface PlayerRepository {
    fun play(audioUrl: String)
    fun pauseResume()
    suspend fun volumeUp()
    suspend fun volumeDown()
    @Composable
    fun PlayerStatusIndicator()
}