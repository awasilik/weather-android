package com.elke.weather.repository.api

import com.elke.weather.domain.location.Location
import com.elke.weather.repository.api.response.ApiResponse

internal interface WeatherRepository {
    fun getData(location: Location): ApiResponse
}