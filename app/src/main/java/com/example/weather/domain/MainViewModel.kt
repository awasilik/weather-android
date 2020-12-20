package com.example.weather.domain

import com.example.weather.domain.model.WeatherData
import com.example.weather.domain.model.WeatherDataProcessor

class MainViewModel(
    private val weatherDataProcessor: WeatherDataProcessor = WeatherDataProcessor()
) {

    val locationList : Array<Location> = Location.values()

    var currentLocation : Location = Location.TARNOBRZEG

    var currentWeather : WeatherData? = null

    var forecast : List<WeatherData> = listOf()

    fun refreshData() {
        currentWeather = weatherDataProcessor.getCurrentWeather(currentLocation)
        forecast = weatherDataProcessor.getForecast(currentLocation, 10)
    }
}