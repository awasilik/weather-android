package com.example.weather.repository.response

data class ApiResponse<T>(val statusCode: Int, val statusMessage: String, val data: T)