package org.onedroid.radiowave.app.presentation.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextOverflow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.onedroid.radiowave.app.domain.Radio
import org.onedroid.radiowave.core.theme.Shapes
import org.onedroid.radiowave.core.theme.extraLarge
import org.onedroid.radiowave.core.theme.extraSmall
import org.onedroid.radiowave.core.theme.imageSize
import org.onedroid.radiowave.core.theme.small
import org.onedroid.radiowave.core.theme.thin
import radiowave.composeapp.generated.resources.Res
import radiowave.composeapp.generated.resources.broken_image_radio
import radiowave.composeapp.generated.resources.right_arrow
import radiowave.composeapp.generated.resources.unknown

@Composable
fun RadioListItem(
    radio: Radio,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val scale by animateFloatAsState(targetValue = if (isHovered) 1.04f else 1f)

    Surface(
        modifier = modifier
            .padding(extraSmall)
            .clip(Shapes.small)
            .scale(scale)
            .clickable(onClick = onClick)
            .hoverable(interactionSource = interactionSource)
            .then(Modifier.fillMaxWidth()),
        tonalElevation = thin,
        shape = Shapes.small,
    ) {
        Row(
            modifier = Modifier
                .padding(small)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(small)
        ) {
            Box(
                modifier = Modifier.size(imageSize),
                contentAlignment = Alignment.Center
            ) {
                radio.imgUrl?.let { imageUrl ->
                    CustomAsyncImage(
                        imageUrl = imageUrl,
                        contentDescription = radio.name,
                        errorImage = painterResource(Res.drawable.broken_image_radio),
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(Shapes.small)
                    )
                }
            }

            Spacer(modifier = Modifier.width(extraSmall))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = radio.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Country: " + (radio.country?.takeIf { it.isNotBlank() }
                        ?: stringResource(Res.string.unknown)),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Language: " + (radio.language?.firstOrNull { it.isNotBlank() }
                        ?.replaceFirstChar { it.uppercase() }
                        ?: stringResource(Res.string.unknown)),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Bitrate: " + ((radio.bitrate?.toString()
                        .takeIf { it?.isNotBlank() == true } ?: "0") + " kbps"),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Icon(
                modifier = Modifier.size(extraLarge),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = stringResource(Res.string.right_arrow)
            )
        }
    }
}