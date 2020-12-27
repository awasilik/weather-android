package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.Location
import com.example.weather.domain.model.WeatherData
import com.example.weather.domain.model.WeatherDataProcessor
import kotlinx.coroutines.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class WeatherViewModel(
    private val weatherDataProcessor: WeatherDataProcessor = WeatherDataProcessor()
) : ViewModel() {

    private val defaultLocation = Location.TARNOBRZEG

    val retrievingData = MutableLiveData<Boolean>()

    val locationList = Location.values()

    val currentLocation = MutableLiveData<Location>().apply { value = defaultLocation }

    val weather = MutableLiveData<WeatherData>()

    val forecast = MutableLiveData<List<WeatherData>>()

    val currentTimeString = MutableLiveData<String>()

    fun refreshData() {
        viewModelScope.launch {
            retrievingData.value = true

            val weatherJob = async { getWeather() }
            val forecastJob = async { getForecast() }

            weather.value = weatherJob.await()
            forecast.value = forecastJob.await()
            currentTimeString.value = getTimeString()

            retrievingData.value = false
        }
    }

    private suspend fun getWeather() = withContext(Dispatchers.Default) {
        weatherDataProcessor.getCurrentWeather(currentLocation.value!!)
    }

    private suspend fun getForecast() = withContext(Dispatchers.Default) {
        weatherDataProcessor.getForecast(currentLocation.value!!, 10)
    }

    private fun getTimeString() : String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return LocalTime.now().format(formatter)
    }
}