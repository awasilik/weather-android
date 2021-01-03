package com.example.weather.domain.dataProcessors

import com.example.weather.domain.model.HourlyWeather
import com.example.weather.repository.model.Hourly

class HourlyForecastDataProcessor : DataProcessor<Hourly, HourlyWeather>() {
    override fun process(apiData: Hourly) =
        HourlyWeather(
            parseTime(apiData.time),
            parseTemperature(apiData.temperature),
            parseTemperature(apiData.temperature),
            apiData.windSpeed!!,
            apiData.pressure!!,
            apiData.humidity!!,
            apiData.cloudiness!!,
            parseRainChancePercent(apiData.rainChance),
            parseImageUrl(apiData.description?.get(0)?.icon)
        )
}