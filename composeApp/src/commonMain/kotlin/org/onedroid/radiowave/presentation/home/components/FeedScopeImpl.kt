package org.onedroid.radiowave.presentation.home.components

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.runtime.Composable

internal class FeedScopeImpl : FeedScope {
    val items = mutableListOf<FeedItem>()
    override fun item(
        key: Any?,
        span: (LazyGridItemSpanScope.() -> GridItemSpan)?,
        contentType: Any?,
        content: @Composable LazyGridItemScope.() -> Unit
    ) {
        items.add(
            FeedItem(
                count = 1,
                key = if (key != null) {
                    { key }
                } else {
                    null
                },
                span = if (span != null) {
                    { span() }
                } else {
                    null
                },
                contentType = { contentType },
                itemContent = { content() }
            )
        )
    }

    override fun items(
        count: Int,
        key: ((index: Int) -> Any)?,
        span: (LazyGridItemSpanScope.(index: Int) -> GridItemSpan)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable LazyGridItemScope.(index: Int) -> Unit
    ) {
        items.add(
            FeedItem(
                count = count,
                key = key,
                span = span,
                contentType = contentType,
                itemContent = itemContent
            )
        )
    }
}

internal data class FeedItem(
    val count: Int,
    val key: ((index: Int) -> Any)?,
    val span: (LazyGridItemSpanScope.(index: Int) -> GridItemSpan)?,
    val contentType: (index: Int) -> Any?,
    val itemContent: @Composable LazyGridItemScope.(index: Int) -> Unit
)