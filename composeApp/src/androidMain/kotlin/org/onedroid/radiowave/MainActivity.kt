package org.onedroid.radiowave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.onedroid.radiowave.app.App
import org.onedroid.radiowave.app.utils.setActivityProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActivityProvider { this }
        installSplashScreen()
        setContent {
            enableEdgeToEdge(
                SystemBarStyle.dark(MaterialTheme.colorScheme.onSurface.toArgb()),
                SystemBarStyle.dark(MaterialTheme.colorScheme.onSurface.toArgb()),
            )
            App()
        }
    }
}