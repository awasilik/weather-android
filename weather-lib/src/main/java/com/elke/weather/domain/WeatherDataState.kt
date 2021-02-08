package com.elke.weather.domain

import com.elke.weather.domain.model.WeatherData

sealed class WeatherDataState {
    class Empty(): WeatherDataState()
    class Loading(): WeatherDataState()
    class Success(val data: WeatherData): WeatherDataState()
    class Failure(val errorMessage: String) : WeatherDataState()
}