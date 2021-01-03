package com.example.weather.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.weather.domain.WeatherDataHolder
import com.example.weather.domain.model.DailyWeather

class ForecastViewModel @ViewModelInject constructor(
    private val weatherDataHolder: WeatherDataHolder) : ViewModel() {

    private val dailyForecastObserver = Observer<List<DailyWeather>> { forecast.value = it }

    val forecast = MutableLiveData<List<DailyWeather>>()

    init {
        setupObservers()
    }

    override fun onCleared() {
        super.onCleared()
        removeObservers()
    }

    private fun setupObservers(){
        weatherDataHolder.dailyForecast.observeForever(dailyForecastObserver)
    }

    private fun removeObservers() {
        weatherDataHolder.dailyForecast.removeObserver(dailyForecastObserver)
    }
}