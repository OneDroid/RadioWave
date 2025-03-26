package org.onedroid.radiowave.app.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.onedroid.radiowave.app.data.mappers.toUiText
import org.onedroid.radiowave.app.domain.Radio
import org.onedroid.radiowave.app.domain.RadioRepository
import org.onedroid.radiowave.core.utils.UiText
import org.onedroid.radiowave.core.utils.onError
import org.onedroid.radiowave.core.utils.onSuccess

class HomeViewModel(
    private val radioRepository: RadioRepository
) : ViewModel() {

    var radios by mutableStateOf<List<Radio>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMsg by mutableStateOf<UiText?>(null)
        private set

    var isSearchActive by mutableStateOf(false)
        private set

    var searchQuery by mutableStateOf("")
        private set

    init {
        getRadios(20)
    }

    fun toggleSearch() {
        isSearchActive = !isSearchActive
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    private fun getRadios(page: Int) = viewModelScope.launch {
        isLoading = true
        radioRepository.getRadios(page = page).onSuccess {
            isLoading = false
            radios = it
        }.onError { error ->
            errorMsg = error.toUiText()
        }
    }
}