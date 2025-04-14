package org.onedroid.radiowave.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.onedroid.radiowave.app.player.PlayerRepository
import org.onedroid.radiowave.app.utils.MAX_RADIO_TO_FETCH
import org.onedroid.radiowave.app.utils.SEARCH_TRIGGER_CHAR
import org.onedroid.radiowave.app.utils.UiText
import org.onedroid.radiowave.app.utils.onError
import org.onedroid.radiowave.app.utils.onSuccess
import org.onedroid.radiowave.data.mappers.toUiText
import org.onedroid.radiowave.domain.Radio
import org.onedroid.radiowave.domain.RadioRepository

class HomeViewModel(
    private val radioRepository: RadioRepository,
    val playerRepository: PlayerRepository
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

    var selectedRadio by mutableStateOf<Radio?>(null)
        private set

    var isPlaying by mutableStateOf(false)
        private set

    var isAboutDialogShowing by mutableStateOf(false)
        private set

    private var offset by mutableStateOf(0)
    private var limit by mutableStateOf(MAX_RADIO_TO_FETCH)

    init {
        getRadios()
        if (cachedRadios.isEmpty()) {
            observeSearchQuery()
        }
    }

    fun toggleAboutDialog() {
        isAboutDialogShowing = !isAboutDialogShowing
    }

    fun toggleSearch() {
        isSearchActive = !isSearchActive
    }

    fun play(streamUrl: String) {
        isPlaying = true
        playerRepository.play(streamUrl)
    }

    fun togglePlayPause() {
        isPlaying = !isPlaying
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun selectedRadio(radio: Radio) {
        selectedRadio = radio
    }

    fun getRadios() = viewModelScope.launch {
        isLoading = true
        radioRepository.getRadios(
            offset = offset,
            limit = limit
        ).onSuccess {
            val allRadios = radios + it
            isLoading = false
            radios = allRadios
            offset += limit
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

                    query.length >= SEARCH_TRIGGER_CHAR -> {
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


    override fun onCleared() {
        super.onCleared()
        playerRepository.onCleared()
    }

}