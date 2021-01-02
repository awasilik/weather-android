package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.WeatherDataHolder
import com.example.weather.domain.model.*
import com.example.weather.domain.dataProcessors.WeatherDataProcessor
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class WeatherViewModel(
    private val weatherDataHolder: WeatherDataHolder = WeatherDataHolder.instance
) : ViewModel() {

    private val currentWeatherObserver = Observer<CurrentWeather> { weather.value = it }
    private val hourlyForecastObserver = Observer<List<HourlyWeather>> { hourlyForecast.value = it.today() }

    val weather = MutableLiveData<CurrentWeather>()

    val hourlyForecast = MutableLiveData<List<HourlyWeather>>()

    init {
        setupObservers()
    }

    override fun onCleared() {
        super.onCleared()
        removeObservers()
    }

    private fun setupObservers() {
        weatherDataHolder.currentWeather.observeForever(currentWeatherObserver)
        weatherDataHolder.hourlyForecast.observeForever(hourlyForecastObserver)
    }

    private fun removeObservers() {
        weatherDataHolder.currentWeather.removeObserver(currentWeatherObserver)
        weatherDataHolder.hourlyForecast.removeObserver(hourlyForecastObserver)
    }

    private fun List<HourlyWeather>.today() =
        this.filter { weather -> weather.time.dayOfMonth == LocalDateTime.now().dayOfMonth }
}