package com.example.weather.repository

import com.example.weather.domain.model.Location
import com.example.weather.repository.response.ApiResponse

interface WeatherRepository {
    fun getData(location: Location): ApiResponse
}