package org.onedroid.radiowave.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.onedroid.radiowave.app.theme.extraSmall
import org.onedroid.radiowave.app.theme.thin
import radiowave.composeapp.generated.resources.Res
import radiowave.composeapp.generated.resources.ic_bookmark_border
import radiowave.composeapp.generated.resources.ic_internet
import radiowave.composeapp.generated.resources.ic_share

@Composable
fun RadioActionButtons() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Button(
                modifier = Modifier.padding(horizontal = thin).width(110.dp),
                onClick = { /* TODO: Save action */ }
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(Res.drawable.ic_bookmark_border),
                    contentDescription = "Save"
                )
                Spacer(modifier = Modifier.width(extraSmall))
                Text(text = "Save")
            }
        }

        item {
            Button(
                modifier = Modifier.padding(horizontal = thin).width(115.dp),
                onClick = { /* TODO: Share action */ }
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(Res.drawable.ic_share),
                    contentDescription = "Share"
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Share")
            }
        }

        item {
            Button(
                modifier = Modifier.padding(horizontal = thin).width(145.dp),
                onClick = { /* TODO: Webpage action */ }
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(Res.drawable.ic_internet),
                    contentDescription = "Webpage"
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Webpage")
            }
        }
    }
}