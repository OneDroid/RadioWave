package org.onedroid.radiowave.app.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

actual class PlayerController(context: Context) : PlayerRepository {

    private val player = ExoPlayer.Builder(context).build()

    override fun play(audioUrl: String) {
        player.clearMediaItems()
        val mediaItem = MediaItem.fromUri(audioUrl)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun pauseResume() {
        if (player.isPlaying) {
            player.pause()
        } else {
            player.play()
        }
    }

    fun release() {
        player.release()
    }
}