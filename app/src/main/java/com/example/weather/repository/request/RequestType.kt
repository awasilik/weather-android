package com.example.weather.repository.request

sealed class RequestType(val mode: String) {

    class Forecast(val amount: Int) : RequestType("forecast")

    class Weather: RequestType("weather")
}