package com.example.weather.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.weather.domain.RainChanceCalculationStrategy
import com.example.weather.domain.RainChanceCalculator
import com.example.weather.domain.WeatherDataHolder
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.HourlyForecast
import java.time.LocalDateTime

class WeatherViewModel @ViewModelInject constructor(
    private val weatherDataHolder: WeatherDataHolder,
    private val rainChanceCalculator: RainChanceCalculator
) : ViewModel() {

    private val hourlyForecastRange = 12

    private val currentWeatherObserver = Observer<CurrentWeather> { weather.value = it }
    private val hourlyForecastObserver = Observer<List<HourlyForecast>> {
        val rangedForecast = it.take(hourlyForecastRange)
        hourlyForecast.value = rangedForecast
        rainChance.value = rainChanceCalculator.calculate(rangedForecast, RainChanceCalculationStrategy.Highest)
    }

    val weather = MutableLiveData<CurrentWeather>()

    val hourlyForecast = MutableLiveData<List<HourlyForecast>>()

    val rainChance = MutableLiveData<Int>()

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
}