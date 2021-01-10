package com.example.weather.domain

import com.example.weather.domain.model.HourlyForecast
import javax.inject.Inject

class RainChanceCalculator @Inject constructor() {
    fun calculate(forecastList: List<HourlyForecast>, strategy: RainChanceCalculationStrategy) =
        strategy.invoke(forecastList)
}