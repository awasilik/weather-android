package com.example.weather.api

import com.example.weather.domain.model.Location
import com.example.weather.api.response.ApiResponse

interface WeatherRepository {
    fun getData(location: Location): ApiResponse
}