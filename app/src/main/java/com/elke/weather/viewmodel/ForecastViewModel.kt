package com.elke.weather.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.elke.weather.domain.WeatherDataHolder
import com.elke.weather.domain.model.DailyForecast

class ForecastViewModel @ViewModelInject constructor(
    private val weatherDataHolder: WeatherDataHolder) : ViewModel() {

    private val dailyForecastObserver = Observer<List<DailyForecast>> { forecast.value = it.drop(1) }

    val forecast = MutableLiveData<List<DailyForecast>>()

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