package com.elke.weather.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.elke.weather.domain.model.DailyForecast

class ForecastViewModel @ViewModelInject constructor(
    dataHolder: WeatherDataHolder) : ViewModel() {

    val forecast: LiveData<List<DailyForecast>> =
        Transformations.map(dataHolder.weatherData) { it.dailyWeather }
}