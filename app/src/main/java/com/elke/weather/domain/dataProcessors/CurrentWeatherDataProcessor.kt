package com.elke.weather.domain.dataProcessors

import com.elke.weather.domain.model.CurrentWeather
import com.elke.weather.api.model.ApiCurrentWeather

class CurrentWeatherDataProcessor : DataProcessor<ApiCurrentWeather, CurrentWeather>() {
    override fun process(apiData: ApiCurrentWeather) = CurrentWeather(
        parseTime(apiData.time),
        parseTime(apiData.sunrise),
        parseTime(apiData.sunset),
        parseTemperature(apiData.temperature),
        parseTemperature(apiData.temperature),
        apiData.windSpeed!!,
        apiData.pressure!!,
        apiData.humidity!!,
        apiData.cloudiness!!,
        parseImageUrl(apiData.description?.get(0)?.icon)
    )
}