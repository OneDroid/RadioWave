package org.onedroid.radiowave

import androidx.compose.ui.window.ComposeUIViewController
import org.onedroid.radiowave.app.App
import org.onedroid.radiowave.core.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}