package com.example.playlistmaker.data.network

import com.example.playlistmaker.data.NetworkClient
import com.example.playlistmaker.data.dto.BaseResponse
import com.example.playlistmaker.data.dto.TrackSearchResponse

class RetrofitNetworkClient : NetworkClient {
    override fun doRequest(dto: Any): BaseResponse {
        // Временно возвращаем успешный ответ с пустым списком
        val response = TrackSearchResponse(emptyList())
        response.resultCode = 200
        return response
    }
}