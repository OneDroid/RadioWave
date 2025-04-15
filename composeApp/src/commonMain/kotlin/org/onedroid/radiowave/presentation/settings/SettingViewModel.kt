package org.onedroid.radiowave.presentation.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.onedroid.radiowave.app.utils.AppPreferences
import org.onedroid.radiowave.app.utils.Theme
import org.onedroid.radiowave.data.database.RadioWaveDatabase

class SettingViewModel(
    private val appPreferences: AppPreferences,
    private val radioWaveDatabase: RadioWaveDatabase,
) : ViewModel() {

    var theme by mutableStateOf<String?>(appPreferences.getThemeSync())
        private set

    var isThemeDialog by mutableStateOf(false)
        private set

    var isClearDataDialog by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            theme = appPreferences.getTheme()
        }
    }

    fun themeDialogToggle() {
        isThemeDialog = !isThemeDialog
    }

    fun clearDataDialogToggle() {
        isClearDataDialog = !isClearDataDialog
    }

    fun onClearDataClick() {
        viewModelScope.launch(Dispatchers.IO) {
            radioWaveDatabase.clearAllEntities()
            clearDataDialogToggle()
        }
    }

    fun onChangeTheme(theme: Theme) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appPreferences.changeThemeMode(theme.name)
            }
            this@SettingViewModel.theme = theme.name
            themeDialogToggle()
        }
    }
}