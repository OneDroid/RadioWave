package org.onedroid.radiowave.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.rememberAsyncImagePainter
import org.jetbrains.compose.resources.painterResource
import radiowave.composeapp.generated.resources.Res
import radiowave.composeapp.generated.resources.broken_image_radio

@Composable
fun CustomAsyncImage(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: @Composable () -> Unit = { PulseAnimation(modifier = Modifier.fillMaxSize()) },
    errorImage: Painter = painterResource(Res.drawable.broken_image_radio),
    contentScale: ContentScale = ContentScale.Fit,
) {
    var imgLoadResult by remember { mutableStateOf<Result<Painter>?>(null) }

    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        onSuccess = {
            imgLoadResult = if (
                it.painter.intrinsicSize.height > 1 &&
                it.painter.intrinsicSize.width > 1
            ) {
                Result.success(it.painter)
            } else {
                Result.failure(Exception("Invalid Image"))
            }
        },
        onError = {
            imgLoadResult = Result.failure(it.result.throwable)
        }
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (val result = imgLoadResult) {
            null -> placeholder()
            else -> Image(
                modifier = Modifier.fillMaxSize(),
                painter = if (result.isSuccess) painter else errorImage,
                contentDescription = contentDescription,
                contentScale = if (result.isSuccess) contentScale else ContentScale.Crop
            )
        }
    }
}