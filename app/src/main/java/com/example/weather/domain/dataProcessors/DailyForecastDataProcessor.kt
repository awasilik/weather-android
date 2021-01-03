package com.example.weather.domain.dataProcessors

import com.example.weather.domain.model.DailyWeather
import com.example.weather.repository.model.Daily

class DailyForecastDataProcessor: DataProcessor<Daily, DailyWeather>() {
    override fun process(apiData: Daily) = DailyWeather(
        parseTime(apiData.time),
        parseTemperature(apiData.forecastTemperature?.day),
        parseTemperature(apiData.forecastTemperature?.night),
        parseTemperature(apiData.forecastFeelsLike?.day),
        parseTemperature(apiData.forecastFeelsLike?.night),
        apiData.windSpeed!!,
        apiData.pressure!!,
        apiData.humidity!!,
        apiData.cloudiness!!,
        parseRainChancePercent(apiData.rainChance),
        parseImageUrl(apiData.description?.get(0)?.icon)
    )
}