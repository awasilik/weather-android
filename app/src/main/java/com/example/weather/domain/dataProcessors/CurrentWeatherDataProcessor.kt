package com.example.weather.domain.dataProcessors

import com.example.weather.domain.model.CurrentWeather
import com.example.weather.repository.model.Current

class CurrentWeatherDataProcessor : DataProcessor<Current, CurrentWeather>() {
    override fun process(apiData: Current) = CurrentWeather(
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