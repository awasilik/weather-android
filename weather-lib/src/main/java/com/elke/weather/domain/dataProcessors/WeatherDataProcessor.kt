package com.elke.weather.domain.dataProcessors

import com.elke.weather.domain.model.WeatherData
import com.elke.weather.repository.api.model.ApiCurrentWeather
import com.elke.weather.repository.api.model.ApiDaily
import com.elke.weather.repository.api.model.ApiHourlyForecast
import com.elke.weather.repository.api.model.ApiWeather
import javax.inject.Inject

internal class WeatherDataProcessor @Inject constructor() : DataProcessor<ApiWeather, WeatherData>() {

    override fun process(apiData: ApiWeather) = WeatherData(
        getCurrentWeatherData(apiData.current!!),
        apiData.hourly!!.map { getHourlyWeatherData(it) },
        apiData.daily!!.map { getDailyWeatherData(it) }
    )

    private fun getCurrentWeatherData(apiData: ApiCurrentWeather) =
        CurrentWeatherDataProcessor().process(apiData)

    private fun getHourlyWeatherData(apiData: ApiHourlyForecast) =
        HourlyForecastDataProcessor().process(apiData)

    private fun getDailyWeatherData(apiData: ApiDaily) =
        DailyForecastDataProcessor().process(apiData)
}