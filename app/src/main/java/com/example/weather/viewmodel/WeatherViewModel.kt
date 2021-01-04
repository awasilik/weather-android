package com.example.weather.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.weather.domain.WeatherDataHolder
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.HourlyForecast
import java.time.LocalDateTime

class WeatherViewModel @ViewModelInject constructor(
    private val weatherDataHolder: WeatherDataHolder
) : ViewModel() {

    private val currentWeatherObserver = Observer<CurrentWeather> { weather.value = it }
    private val hourlyForecastObserver = Observer<List<HourlyForecast>> { hourlyForecast.value = it.today() }

    val weather = MutableLiveData<CurrentWeather>()

    val hourlyForecast = MutableLiveData<List<HourlyForecast>>()

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

    private fun List<HourlyForecast>.today() =
        this.filter { weather -> weather.time.dayOfMonth == LocalDateTime.now().dayOfMonth }
}