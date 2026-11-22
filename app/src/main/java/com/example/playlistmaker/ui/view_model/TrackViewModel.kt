package com.example.playlistmaker.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.playlistmaker.MyApplication
import com.example.playlistmaker.domain.api.TracksRepository
import com.example.playlistmaker.domain.model.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrackViewModel(
    private val repository: TracksRepository
) : ViewModel() {

    sealed class TrackScreenState {
        object Initial : TrackScreenState()
        object Loading : TrackScreenState()
        data class Content(val track: Track) : TrackScreenState()
    }

    private val _screenState = MutableStateFlow<TrackScreenState>(TrackScreenState.Initial)
    val screenState: StateFlow<TrackScreenState> = _screenState.asStateFlow()

    fun loadTrack(trackId: Long) {
        viewModelScope.launch {
            _screenState.value = TrackScreenState.Loading
            val track = repository.loadTrackDetail(trackId)
            _screenState.value = TrackScreenState.Content(track)
        }
    }

    companion object {
        fun getViewModelFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = (this[APPLICATION_KEY] as MyApplication).getTracksRepository()
                TrackViewModel(repository)
            }
        }
    }
}