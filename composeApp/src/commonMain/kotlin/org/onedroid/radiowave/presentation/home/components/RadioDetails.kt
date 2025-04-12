package org.onedroid.radiowave.presentation.home.components

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import org.onedroid.radiowave.app.theme.medium
import org.onedroid.radiowave.app.theme.small
import org.onedroid.radiowave.domain.Radio

@Composable
fun RadioDetails(
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