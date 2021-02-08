package com.elke.weather.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.elke.weather.domain.model.CurrentWeather
import com.elke.weather.domain.model.HourlyForecast
import com.elke.weather.domain.rainChance.RainChanceCalculationStrategy
import com.elke.weather.domain.rainChance.RainChanceCalculator

class WeatherViewModel @ViewModelInject constructor(
    dataHolder: WeatherDataHolder,
    private val rainChanceCalculator: RainChanceCalculator
) : ViewModel() {

    private val hourlyForecastRange = 12

    val weather: LiveData<CurrentWeather> =
        Transformations.map(dataHolder.weatherData) { it.currentWeather }

    val hourlyForecast: LiveData<List<HourlyForecast>> =
        Transformations.map(dataHolder.weatherData) { it.hourlyWeather.take(hourlyForecastRange) }

    val rainChance: LiveData<Int> =
        Transformations.map(dataHolder.weatherData) {
            rainChanceCalculator.calculate(
                it.hourlyWeather.take(hourlyForecastRange),
                RainChanceCalculationStrategy.Highest)
        }
}