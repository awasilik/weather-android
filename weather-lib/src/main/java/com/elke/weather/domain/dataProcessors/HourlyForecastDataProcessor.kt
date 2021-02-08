package com.elke.weather.domain.dataProcessors

import com.elke.weather.domain.model.HourlyForecast
import com.elke.weather.repository.api.model.ApiHourlyForecast

internal class HourlyForecastDataProcessor : DataProcessor<ApiHourlyForecast, HourlyForecast>() {
    override fun process(apiData: ApiHourlyForecast) =
        HourlyForecast(
            parseTime(apiData.time),
            parseTemperature(apiData.temperature),
            parseTemperature(apiData.temperature),
            parseWindSpeed(apiData.windSpeed),
            parsePressure(apiData.pressure),
            parseHumidity(apiData.humidity),
            parseCloudiness(apiData.cloudiness),
            parseRainChancePercent(apiData.rainChance),
            parseImageUrl(apiData.description)
        )
}