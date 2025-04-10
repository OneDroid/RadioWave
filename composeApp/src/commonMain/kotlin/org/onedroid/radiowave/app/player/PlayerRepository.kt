package org.onedroid.radiowave.app.player

interface PlayerRepository {
    fun play(audioUrl: String)
    fun pauseResume()
}