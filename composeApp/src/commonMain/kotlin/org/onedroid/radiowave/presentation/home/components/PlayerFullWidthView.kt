package org.onedroid.radiowave.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import radiowave.composeapp.generated.resources.Res
import radiowave.composeapp.generated.resources.ic_pause
import radiowave.composeapp.generated.resources.ic_play
import radiowave.composeapp.generated.resources.ic_volume_down
import radiowave.composeapp.generated.resources.ic_volume_up

@Composable
fun PlayerFullWidthView(
    modifier: Modifier = Modifier,
    onPlayClick: () -> Unit = {},
    onVolumeUpClick: () -> Unit = {},
    onVolumeDownClick: () -> Unit = {},
    isPlaying: Boolean = false,
    playerStatusIndicator: @Composable () -> Unit = {}
) {
    Column {
        Spacer(modifier = Modifier.height(15.dp))
        playerStatusIndicator()
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                modifier = Modifier.clip(RoundedCornerShape(100))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .size(60.dp),
                onClick = {
                    onVolumeDownClick()
                }
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(Res.drawable.ic_volume_down),
                    contentDescription = "Collapse",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.width(2.dp))

            IconButton(
                modifier = Modifier.padding(5.dp).clip(RoundedCornerShape(100))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .size(80.dp),
                onClick = {
                    onPlayClick()
                }
            ) {
                Icon(
                    modifier = Modifier.size(45.dp),
                    painter = if (isPlaying) {
                        painterResource(Res.drawable.ic_pause)
                    } else {
                        painterResource(Res.drawable.ic_play)
                    },
                    contentDescription = "Pause or Play",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.width(2.dp))

            IconButton(
                modifier = Modifier.clip(RoundedCornerShape(100))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .size(60.dp),
                onClick = {
                    onVolumeUpClick()
                }
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(Res.drawable.ic_volume_up),
                    contentDescription = "Collapse",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}