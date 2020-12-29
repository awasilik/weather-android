package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.model.*
import com.example.weather.domain.WeatherDataProcessor
import kotlinx.coroutines.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class WeatherViewModel(
    private val weatherDataProcessor: WeatherDataProcessor = WeatherDataProcessor()
) : ViewModel() {

    private val defaultLocation = Location.Tarnobrzeg

    val retrievingData = MutableLiveData<Boolean>()

    val locationList = Location.values()

    val currentLocation = MutableLiveData<Location>().apply { value = defaultLocation }

    val weather = MutableLiveData<CurrentWeather>()

    val hourlyForecast = MutableLiveData<List<HourlyWeather>>()

    val currentTimeString = MutableLiveData<String>()

    val errorMessage = MutableLiveData<String>()

    fun refreshData() {
        viewModelScope.launch {
            retrievingData.value = true

            try {
                val weatherData = fetchData()

                weather.value = weatherData.currentWeather
                hourlyForecast.value = weatherData.hourlyWeather.fromNow()
                errorMessage.value = null
            }
            catch (e : Throwable)
            {
                errorMessage.value = e.message
            }

            currentTimeString.value = getTimeString()
            retrievingData.value = false
        }
    }

    private suspend fun fetchData() = withContext(Dispatchers.Default) {
        weatherDataProcessor.fetchData(currentLocation.value!!).value
    }

    private fun getTimeString() : String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return LocalTime.now().format(formatter)
    }

    private fun List<HourlyWeather>.fromNow() =
        this.filter { weather -> weather.time > LocalTime.now() }
}