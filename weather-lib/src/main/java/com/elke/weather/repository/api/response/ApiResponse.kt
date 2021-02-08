package com.elke.weather.repository.api.response

import com.elke.weather.repository.api.model.ApiWeather

internal data class ApiResponse(val statusCode: Int, val statusMessage: String, val data: ApiWeather)