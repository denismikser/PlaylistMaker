package com.example.playlistmaker.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.playlistmaker.MyApplication
import com.example.playlistmaker.domain.api.TrackSearchInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrackViewModel(
    private val tracksInteractor: TrackSearchInteractor,
) : ViewModel() {

    private var _loadingStateFlow = MutableStateFlow(true)
    fun getLoadingStateFlow(): StateFlow<Boolean> = _loadingStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val data = tracksInteractor.loadSomeData()
            _loadingStateFlow.value = false
        }
    }

    companion object {
        fun getViewModelFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val interactor = (this[APPLICATION_KEY] as MyApplication).provideTrackSearchInteractor()
                TrackViewModel(interactor)
            }
        }
    }
}