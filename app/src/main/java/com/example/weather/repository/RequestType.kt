package com.example.weather.repository

import com.example.weather.domain.Location

sealed class RequestType(private val location: Location, val mode: String) {

    val locationValue: String
        get() = "${location.city},${location.country}"

    class Forecast(location: Location, val amount: Int) : RequestType(location, "forecast")

    class Weather(location: Location) : RequestType(location, "weather")
}