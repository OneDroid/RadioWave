package org.onedroid.radiowave.presentation.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.onedroid.radiowave.app.utils.Theme
import org.onedroid.radiowave.presentation.settings.components.ClearDataDialog
import org.onedroid.radiowave.presentation.settings.components.SettingItem
import org.onedroid.radiowave.presentation.settings.components.ThemeSelectionDialog
import radiowave.composeapp.generated.resources.Res
import radiowave.composeapp.generated.resources.clear_data
import radiowave.composeapp.generated.resources.go_back
import radiowave.composeapp.generated.resources.setting
import radiowave.composeapp.generated.resources.theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    rootNavController: NavController,
    viewModel: SettingViewModel
) {
    when {
        viewModel.isThemeDialog -> {
            ThemeSelectionDialog(
                currentTheme = viewModel.theme ?: Theme.SYSTEM_DEFAULT.name,
                onThemeChange = { theme ->
                    viewModel.onChangeTheme(theme)
                },
                onDismissRequest = {
                    viewModel.themeDialogToggle()
                }
            )
        }

        viewModel.isClearDataDialog -> {
            ClearDataDialog(
                onDeleteHistory = {
                    viewModel.onClearDataClick()
                },
                onDismissRequest = {
                    viewModel.clearDataDialogToggle()
                },
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(Res.string.setting))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        rootNavController.navigateUp()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            stringResource(Res.string.go_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            item {
                SettingItem(
                    onClick = {
                        viewModel.themeDialogToggle()
                    },
                    painter = Icons.Filled.Settings,
                    itemName = stringResource(Res.string.theme)
                )
            }
            item {
                SettingItem(
                    onClick = {
                        viewModel.clearDataDialogToggle()
                    },
                    painter = Icons.Filled.Delete,
                    itemName = stringResource(Res.string.clear_data)
                )
            }
        }
    }
}