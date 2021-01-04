package com.example.weather.repository.response

import com.example.weather.repository.model.ApiWeather

data class ApiResponse(val statusCode: Int, val statusMessage: String, val data: ApiWeather)