package com.example.weather.viewmodel

import com.example.weather.domain.WeatherDataProcessor
import com.example.weather.domain.model.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppWidgetViewModel(private val dataProcessor: WeatherDataProcessor = WeatherDataProcessor())  {

    val location: Location
        get() = Location.TARNOBRZEG

    suspend fun getTemperature() = withContext(Dispatchers.Default) {
        dataProcessor.getCurrentWeather(Location.TARNOBRZEG).value.temperature
    }
}