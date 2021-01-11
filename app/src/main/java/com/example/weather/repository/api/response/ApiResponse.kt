package com.example.weather.repository.api.response

import com.example.weather.repository.api.model.ApiWeather
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("statusCode")
    val statusCode: Int,
    val statusMessage: String,
    val data: ApiWeather
)