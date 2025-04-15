package org.onedroid.radiowave.presentation.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import org.onedroid.radiowave.app.theme.large
import org.onedroid.radiowave.app.theme.medium

@Composable
fun SettingItem(
    onClick: () -> Unit,
    painter: ImageVector,
    itemName: String,
    itemColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }.padding(medium),
        horizontalArrangement = Arrangement.spacedBy(medium, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(large),
            imageVector = painter,
            contentDescription = null,
        )
        Text(
            text = itemName,
            color = itemColor,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal
        )
    }
}