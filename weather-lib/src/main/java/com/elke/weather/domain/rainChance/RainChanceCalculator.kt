package com.elke.weather.domain.rainChance

import com.elke.weather.domain.model.HourlyForecast
import javax.inject.Inject

class RainChanceCalculator @Inject constructor() {
    fun calculate(forecastList: List<HourlyForecast>, strategy: RainChanceCalculationStrategy) =
        strategy.invoke(forecastList)
}