package com.elke.weather.domain.dataProcessors

import com.elke.weather.domain.model.CurrentWeather
import com.elke.weather.repository.api.model.ApiCurrentWeather

internal class CurrentWeatherDataProcessor : DataProcessor<ApiCurrentWeather, CurrentWeather>() {
    override fun process(apiData: ApiCurrentWeather) = CurrentWeather(
        parseTime(apiData.time),
        parseTime(apiData.sunrise),
        parseTime(apiData.sunset),
        parseTemperature(apiData.temperature),
        parseTemperature(apiData.temperature),
        parseWindSpeed(apiData.windSpeed),
        parsePressure(apiData.pressure),
        parseHumidity(apiData.humidity),
        parseCloudiness(apiData.cloudiness),
        parseImageUrl(apiData.description)
    )
}