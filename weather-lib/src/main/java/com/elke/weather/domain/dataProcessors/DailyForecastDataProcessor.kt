package com.elke.weather.domain.dataProcessors

import com.elke.weather.domain.model.DailyForecast
import com.elke.weather.repository.api.model.ApiDaily

internal class DailyForecastDataProcessor: DataProcessor<ApiDaily, DailyForecast>() {
    override fun process(apiData: ApiDaily) = DailyForecast(
        parseTime(apiData.time),
        parseTemperature(apiData.forecastTemperature?.day),
        parseTemperature(apiData.forecastTemperature?.night),
        parseTemperature(apiData.forecastFeelsLike?.day),
        parseTemperature(apiData.forecastFeelsLike?.night),
        parseWindSpeed(apiData.windSpeed),
        parsePressure(apiData.pressure),
        parseHumidity(apiData.humidity),
        parseCloudiness(apiData.cloudiness),
        parseRainChancePercent(apiData.rainChance),
        parseImageUrl(apiData.description)
    )
}