package com.example.weather.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.model.WeatherData
import com.example.weather.domain.model.WeatherDataProcessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val weatherDataProcessor: WeatherDataProcessor = WeatherDataProcessor()
) : ViewModel() {

    val locationList : Array<Location> = Location.values()

    var currentLocation : Location = Location.TARNOBRZEG

    var currentWeather : WeatherData? = null

    var forecast : List<WeatherData> = listOf()

    fun refreshData() {
        viewModelScope.launch {
            loadData()
        }
    }

    private suspend fun loadData() = withContext(Dispatchers.Default)
    {
        currentWeather = weatherDataProcessor.getCurrentWeather(currentLocation)
        forecast = weatherDataProcessor.getForecast(currentLocation, 10)
    }
}