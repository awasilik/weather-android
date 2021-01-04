package com.example.weather.api.response

import com.example.weather.api.model.ApiWeather

data class ApiResponse(val statusCode: Int, val statusMessage: String, val data: ApiWeather)