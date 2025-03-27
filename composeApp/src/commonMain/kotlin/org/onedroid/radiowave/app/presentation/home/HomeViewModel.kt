package org.onedroid.radiowave.app.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
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

    private val cachedRadios = emptyList<Radio>()
    private var searchJob: Job? = null

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

    var isSearchLoading by mutableStateOf(false)
        private set

    var searchErrorMsg by mutableStateOf<UiText?>(null)
        private set

    var searchResult by mutableStateOf<List<Radio>>(emptyList())
        private set

    init {
        getRadios(20)
        if (cachedRadios.isEmpty()) {
            observeSearchQuery()
        }
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

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            snapshotFlow { searchQuery }.distinctUntilChanged().debounce(500L).collect { query ->
                when {
                    query.isBlank() -> {
                        searchErrorMsg = null
                        searchResult = cachedRadios
                    }

                    query.length >= 3 -> {
                        searchJob?.cancel()
                        searchJob = searchRadios(query)
                    }
                }
            }
        }
    }

    private fun searchRadios(query: String) = viewModelScope.launch {
        isSearchLoading = true
        radioRepository.searchRadios(query).onSuccess {
            isSearchLoading = false
            searchErrorMsg = null
            searchResult = it
        }.onError { error ->
            searchResult = emptyList()
            isSearchLoading = false
            searchErrorMsg = error.toUiText()
        }
    }
}