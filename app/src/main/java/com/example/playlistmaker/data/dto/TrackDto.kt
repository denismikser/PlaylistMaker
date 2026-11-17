package com.example.playlistmaker.data.dto

import com.example.playlistmaker.domain.model.Track

data class TrackDto(
    val id: Long,
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Long,
)

fun TrackDto.toDomain(): Track {
    return Track(
        id = this.id,
        trackName = this.trackName,
        artistName = this.artistName,
        // Конвертируем миллисекунды в формат mm:ss
        trackTime = formatTime(this.trackTimeMillis)
    )
}

private fun formatTime(millis: Long): String {
    val minutes = (millis / 1000) / 60
    val seconds = (millis / 1000) % 60
    return String.format("%02d:%02d", minutes, seconds)
}