package org.onedroid.radiowave.presentation.home.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import org.onedroid.radiowave.app.theme.Shapes
import org.onedroid.radiowave.app.theme.extraSmall
import org.onedroid.radiowave.app.theme.horizontalGridMaxHeight
import org.onedroid.radiowave.app.theme.imageSize
import org.onedroid.radiowave.app.theme.large
import org.onedroid.radiowave.app.theme.medium
import org.onedroid.radiowave.app.theme.shimmerColors
import org.onedroid.radiowave.app.theme.small
import org.onedroid.radiowave.app.theme.thin

object ShimmerEffect {
    @Composable
    fun ShimmerBrush(): Brush {
        val transition = rememberInfiniteTransition()
        val translateAnimation by transition.animateFloat(
            initialValue = 0f,
            targetValue = 400f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 1500, easing = LinearOutSlowInEasing),
                RepeatMode.Reverse
            ),
        )
        return remember {
            derivedStateOf {
                Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(translateAnimation, translateAnimation),
                    end = Offset(translateAnimation + 100f, translateAnimation + 100f),
                    tileMode = TileMode.Mirror,
                )
            }
        }.value
    }

    @Composable
    fun RadioHorizontalGridItemShimmerEffect() {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier.heightIn(max = horizontalGridMaxHeight),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(30) {
                RadioGridItemShimmer()
            }
        }
    }

    @Composable
    fun RadioGridItemShimmer() {
        val brush = ShimmerBrush()
        Surface(
            modifier = Modifier
                .padding(thin)
                .clip(Shapes.small)
                .then(Modifier.fillMaxWidth()),
            tonalElevation = 2.dp,
            shape = Shapes.small,
        ) {
            Column(
                modifier = Modifier
                    .padding(small)
                    .size(imageSize),
            ) {

                Box(
                    modifier = Modifier
                        .size(imageSize)
                        .aspectRatio(1f)
                        .clip(Shapes.small)
                        .background(brush)
                )

                Spacer(modifier = Modifier.height(small))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(large - extraSmall)
                        .clip(Shapes.small)
                        .background(brush)
                )

                Spacer(modifier = Modifier.height(small))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.6f)
                        .height(medium)
                        .clip(Shapes.small)
                        .background(brush)
                )
            }
        }
    }
}