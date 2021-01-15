package com.elke.weather.domain.dataProcessors

import com.elke.weather.domain.model.HourlyForecast
import com.elke.weather.api.model.ApiHourlyForecast

class HourlyForecastDataProcessor : DataProcessor<ApiHourlyForecast, HourlyForecast>() {
    override fun process(apiData: ApiHourlyForecast) =
        HourlyForecast(
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