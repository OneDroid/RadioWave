package org.onedroid.radiowave.presentation.home.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.onedroid.radiowave.app.theme.extraSmall
import org.onedroid.radiowave.app.theme.medium
import org.onedroid.radiowave.app.theme.small
import org.onedroid.radiowave.domain.Radio
import radiowave.composeapp.generated.resources.Res
import radiowave.composeapp.generated.resources.broken_image_radio
import radiowave.composeapp.generated.resources.ic_circle
import radiowave.composeapp.generated.resources.ic_pause
import radiowave.composeapp.generated.resources.ic_play
import radiowave.composeapp.generated.resources.ic_volume_down
import radiowave.composeapp.generated.resources.ic_volume_up
import radiowave.composeapp.generated.resources.radio_cover
import radiowave.composeapp.generated.resources.radio_cover_background

@Composable
fun RadioDetailsBottomSheet(
    radio: Radio,
    isPlaying: Boolean = false,
    onPlayClick: () -> Unit = {},
    onVolumeUpClick: () -> Unit = {},
    onVolumeDownClick: () -> Unit = {},
    playerStatusIndicator: @Composable () -> Unit = {},
    nowPlayingIndicator: @Composable () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(small)
    ) {
        item {
            radio.imgUrl?.let { imgUrl ->
                val imagePainter = rememberAsyncImagePainter(
                    model = imgUrl,
                    placeholder = painterResource(Res.drawable.broken_image_radio),
                    error = painterResource(Res.drawable.broken_image_radio)
                )
                AnimatedRadioCoverImage(
                    isPlaying = isPlaying,
                    painter = imagePainter,
                )
            }
        }
        item {
            radioDetail(
                radio = radio,
                nowPlayingIndicator = nowPlayingIndicator,
            )
        }
        item {
            ActionButtons()
        }

        item {
            PlayerFullWidthView(
                onPlayClick = onPlayClick,
                onVolumeUpClick = onVolumeUpClick,
                onVolumeDownClick = onVolumeDownClick,
                isPlaying = isPlaying,
                playerStatusIndicator = playerStatusIndicator
            )
        }
    }

}

@Composable
fun radioDetail(
    radio: Radio,
    nowPlayingIndicator: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(small)
    ) {
        Spacer(modifier = Modifier.height(medium))
        Text(
            text = radio.name.trimStart(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        radio.country?.let {
            Text(
                modifier = Modifier.basicMarquee(
                    initialDelayMillis = 0,
                    iterations = Int.MAX_VALUE,
                ),
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        nowPlayingIndicator()
    }
}

@Composable
private fun ActionButtons() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        listOf("Save", "Share", "Webpage").forEach { label ->
            item {
                Button(
                    modifier = Modifier.padding(horizontal = extraSmall),
                    onClick = { /* TODO: Add respective actions */ }
                ) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
private fun AnimatedRadioCoverImage(
    modifier: Modifier = Modifier,
    isPlaying: Boolean = false,
    painter: Painter
) {
    var currentRotation by remember {
        mutableFloatStateOf(0f)
    }
    val rotation = remember {
        Animatable(currentRotation)
    }
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            rotation.animateTo(
                targetValue = currentRotation + 360f, animationSpec = infiniteRepeatable(
                    animation = tween(5000, easing = LinearEasing), repeatMode = RepeatMode.Restart
                )
            ) {
                currentRotation = value
            }
        } else {
            if (currentRotation > 0f) {
                rotation.animateTo(
                    targetValue = currentRotation + 50, animationSpec = tween(
                        1250, easing = LinearOutSlowInEasing
                    )
                ) {
                    currentRotation = value
                }
            }
        }
    }
    RadioCoverImage(
        modifier = modifier,
        painter = painter,
        rotationDegrees = rotation.value
    )
}

@Composable
private fun RadioCoverImage(
    painter: Painter,
    modifier: Modifier = Modifier,
    rotationDegrees: Float = 0f
) {
    Box(
        modifier = modifier
            .aspectRatio(1.0f)
            .clip(RoundedCornerShape(100)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotationDegrees),
            painter = painterResource(Res.drawable.radio_cover_background),
            contentDescription = stringResource(Res.string.radio_cover_background)
        )
        Image(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .rotate(rotationDegrees)
                .aspectRatio(1.0f)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(100)),
            painter = painter,
            contentDescription = stringResource(Res.string.radio_cover)
        )
        /* Box(
             modifier = Modifier
                 .size(40.dp)
                 .clip(RoundedCornerShape(100))
                 .background(Color.Black)
                 .padding(5.dp)
                 .clip(RoundedCornerShape(100))
                 .background(MaterialTheme.colorScheme.background)
                 .align(Alignment.Center)
         )*/
    }
}

@Composable
private fun PlayerFullWidthView(
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