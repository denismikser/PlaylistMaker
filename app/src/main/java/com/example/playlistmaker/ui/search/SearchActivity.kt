package com.example.playlistmaker.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlistmaker.domain.model.Track

@Composable
fun DetailsScreen(track: Track) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Название: ${track.trackName}", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Исполнитель: ${track.artistName}", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Длительность: ${track.trackTime}", fontSize = 16.sp)
    }
}