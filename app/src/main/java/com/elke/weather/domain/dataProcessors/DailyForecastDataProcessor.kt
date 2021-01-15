package com.elke.weather.domain.dataProcessors

import com.elke.weather.domain.model.DailyForecast
import com.elke.weather.api.model.ApiDaily

class DailyForecastDataProcessor: DataProcessor<ApiDaily, DailyForecast>() {
    override fun process(apiData: ApiDaily) = DailyForecast(
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