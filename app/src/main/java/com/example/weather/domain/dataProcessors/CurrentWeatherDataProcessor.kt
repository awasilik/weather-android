package com.example.weather.domain.dataProcessors

import com.example.weather.domain.model.CurrentWeather
import com.example.weather.repository.api.model.ApiCurrentWeather
import java.lang.IllegalStateException

class CurrentWeatherDataProcessor : DataProcessor<ApiCurrentWeather, CurrentWeather>() {
    override fun process(apiData: ApiCurrentWeather) = CurrentWeather(
        // do not use !!. Domain should describe what should happen if api data is null, it can be null as well
        parseTime(apiData.time),
        parseTime(apiData.sunrise),
        parseTime(apiData.sunset),
        parseTemperature(apiData.temperature),
        parseTemperature(apiData.temperature),
        apiData.windSpeed ?: throw IllegalStateException(),
        apiData.pressure!!,
        apiData.humidity!!,
        apiData.cloudiness!!,
        parseImageUrl(apiData.description?.get(0)?.icon)
    )
}