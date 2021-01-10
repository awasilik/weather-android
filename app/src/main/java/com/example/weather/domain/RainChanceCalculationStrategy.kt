package com.example.weather.domain

import com.example.weather.domain.model.HourlyForecast
import kotlin.math.roundToInt

sealed class RainChanceCalculationStrategy : (List<HourlyForecast>) -> Int {
    object Highest : RainChanceCalculationStrategy() {
        override fun invoke(forecasts: List<HourlyForecast>) =
            forecasts.map { it.rainChance }.maxOrNull()!!
    }

    object Average : RainChanceCalculationStrategy() {
        override fun invoke(forecasts: List<HourlyForecast>) =
            forecasts.map { it.rainChance }.average().roundToInt()
    }

    object AverageDuringDay : RainChanceCalculationStrategy() {
        override fun invoke(forecasts: List<HourlyForecast>): Int {
            val dayHourRange = 7..18
            return forecasts
                .filter { dayHourRange.contains(it.time.hour) }
                .map { it.rainChance }
                .average()
                .roundToInt()
        }
    }
}
