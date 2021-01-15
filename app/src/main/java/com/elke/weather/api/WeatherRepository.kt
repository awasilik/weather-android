package com.elke.weather.api

import com.elke.weather.domain.model.Location
import com.elke.weather.api.response.ApiResponse

interface WeatherRepository {
    fun getData(location: Location): ApiResponse
}