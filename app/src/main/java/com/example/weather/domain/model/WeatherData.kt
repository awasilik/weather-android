package com.example.weather.domain.model

data class WeatherData(
    val currentWeather: CurrentWeather,
    val hourlyWeather: List<HourlyWeather>,
    val dailyWeather: List<DailyWeather>
)
