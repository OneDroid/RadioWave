package org.onedroid.radiowave.app.player

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.delay
import org.onedroid.radiowave.R

actual class PlayerController(context: Context) : PlayerRepository {

    private val player = ExoPlayer.Builder(context).build()
    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

    private val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

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
        vibrateDevice()
    }

    override suspend fun volumeUp() {
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val newVolume = (currentVolume + 1).coerceAtMost(maxVolume)
        adjustVolumeWithUi(newVolume)
        vibrateDevice()
    }

    override suspend fun volumeDown() {
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val newVolume = (currentVolume - 1).coerceAtLeast(0)
        adjustVolumeWithUi(newVolume)
        vibrateDevice()
    }

    private fun adjustVolumeWithUi(newVolume: Int) {
        audioManager.setStreamVolume(
            AudioManager.STREAM_MUSIC,
            newVolume, 0
        )
    }

    private fun vibrateDevice() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(60, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(60)
        }
    }

    @SuppressLint("DefaultLocale")
    @Composable
    override fun PlayerStatusIndicator() {
        val playbackState = remember { mutableIntStateOf(player.playbackState) }
        val currentPosition = remember { mutableLongStateOf(0L) }
        val hasError = remember { mutableStateOf(false) }

        val currentVolume = remember { mutableIntStateOf(getCurrentVolume()) }

        // Update volume periodically
        LaunchedEffect(Unit) {
            while (true) {
                currentVolume.intValue = getCurrentVolume()
                delay(500) // adjust as needed
            }
        }

        // Monitor player state
        DisposableEffect(player) {
            val listener = object : Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    playbackState.intValue = state
                    // If playback starts successfully, reset error state
                    if (state == Player.STATE_READY || state == Player.STATE_BUFFERING) {
                        hasError.value = false
                    }
                }

                override fun onPlayerError(error: PlaybackException) {
                    hasError.value = true
                }
            }

            player.addListener(listener)

            onDispose {
                player.removeListener(listener)
            }
        }

        // Update timer when playing
        LaunchedEffect(player.isPlaying) {
            while (player.isPlaying) {
                currentPosition.longValue = player.currentPosition
                delay(50L)
            }
        }

        val animatedVolumePercent by animateFloatAsState(
            targetValue = currentVolume.intValue / maxVolume.toFloat(),
            animationSpec = tween(durationMillis = 300),
            label = "volumeAnim"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // LEFT: Live/Offline
            if (hasError.value || playbackState.intValue == Player.STATE_IDLE || playbackState.intValue == Player.STATE_ENDED) {
                OfflineIndicator(4.dp)
            } else {
                LiveIndicator(4.dp)
            }

            // CENTER: Volume progress bar
            LinearProgressIndicator(
                progress = { animatedVolumePercent },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
                    .height(8.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = Color.Green,
                trackColor = Color.DarkGray,
            )

            // RIGHT: Timer or Status
            when {
                hasError.value -> Text("Failed to play", color = Color.Red)
                playbackState.intValue == Player.STATE_BUFFERING -> CircularProgressIndicator(modifier = Modifier.size(16.dp))
                player.isPlaying || playbackState.intValue == Player.STATE_READY -> {
                    val totalSeconds = currentPosition.longValue / 1000
                    val hours = totalSeconds / 3600
                    val minutes = (totalSeconds % 3600) / 60
                    val seconds = totalSeconds % 60
                    Text(
                        textAlign = TextAlign.End,
                        modifier = Modifier.width(65.dp),
                        text = String.format("%d:%02d:%02d", hours, minutes, seconds)
                    )
                }
                else -> Text("Not playing")
            }
        }
    }

    @Composable
    fun LiveIndicator(extraSmall: Dp = 4.dp) {
        var visible by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            while (true) {
                visible = !visible
                delay(500)
            }
        }

        val alpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = tween(durationMillis = 300), label = ""
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(RoundedCornerShape(100))
                    .alpha(alpha),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_circle),
                    contentDescription = "Blinking Dot",
                    modifier = Modifier.size(10.dp),
                    colorFilter = ColorFilter.tint(Color.Red)
                )
            }
            Spacer(modifier = Modifier.width(extraSmall))
            Text(text = "Live")
        }
    }

    @Composable
    fun OfflineIndicator(extraSmall: Dp = 4.dp) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(RoundedCornerShape(100))
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(extraSmall))
            Text(text = "Offline", color = Color.Gray)
        }
    }

    private fun getCurrentVolume(): Int {
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    }
}