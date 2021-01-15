package com.elke.weather.api.response

import com.elke.weather.api.model.ApiWeather

data class ApiResponse(val statusCode: Int, val statusMessage: String, val data: ApiWeather)