package com.example.weather.repository.api

import com.example.weather.domain.model.Location
import com.example.weather.repository.api.response.ApiResponse

interface WeatherRepository {
    fun getData(location: Location): ApiResponse
}