package com.example.playlistmaker.domain.api

import com.example.playlistmaker.domain.model.Track

interface TracksRepository {
    fun searchTracks(expression: String): List<Track>
    suspend fun loadSomeData(): List<Track>
    suspend fun loadTrackDetail(trackId: Long): Track
}