package com.example.weather.domain.model

data class Weather(
    val currentWeather: CurrentWeather,
    val hourlyWeather: List<HourlyForecast>,
    val dailyWeather: List<DailyForecast>
)
