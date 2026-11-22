package com.example.playlistmaker.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.playlistmaker.ui.search.DetailsScreen
import com.example.playlistmaker.ui.theme.PlaylistMakerTheme
import com.example.playlistmaker.ui.view_model.TrackViewModel

class TrackActivity : ComponentActivity() {

    private val viewModel by viewModels<TrackViewModel> { TrackViewModel.getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaylistMakerTheme {
                TrackScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun TrackScreen(viewModel: TrackViewModel) {
    val screenState by viewModel.screenState.collectAsState()

    when (screenState) {
        is TrackViewModel.TrackScreenState.Initial -> {
            LoadingContent(
                viewModel = viewModel,
                text = "Нажмите кнопку для загрузки",
                showProgressBar = false
            )
        }
        is TrackViewModel.TrackScreenState.Loading -> {
            LoadingContent(
                viewModel = viewModel,
                text = "Загрузка...",
                showProgressBar = true
            )
        }
        is TrackViewModel.TrackScreenState.Content -> {
            val state = screenState as TrackViewModel.TrackScreenState.Content
            DetailsScreen(track = state.track)
        }
    }
}

@Composable
private fun LoadingContent(
    viewModel: TrackViewModel,
    text: String,
    showProgressBar: Boolean
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { viewModel.loadTrack(trackId = 123L) }
        ) {
            Text("Загрузить трек")
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (showProgressBar) {
            CircularProgressIndicator(modifier = Modifier.size(48.dp))
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(text)
    }
}