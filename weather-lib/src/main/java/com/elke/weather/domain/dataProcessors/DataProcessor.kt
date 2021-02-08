package com.elke.weather.domain.dataProcessors

import com.elke.weather.repository.api.model.Description
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.math.roundToInt

internal abstract class DataProcessor<Input, Output> {
    abstract fun process(apiData: Input) : Output

    protected fun parseTime(timeStamp: Long?): LocalDateTime =
        Instant.ofEpochSecond(timeStamp!!).atZone(ZoneId.systemDefault()).toLocalDateTime()

    protected fun parseTemperature(temperature: Double?) =
        temperature!!.roundToInt()

    protected fun parseWindSpeed(windSpeed: Double?) =
        windSpeed ?: 0.0

    protected fun parsePressure(pressure: Int?) =
        pressure ?: 0

    protected fun parseHumidity(humidity: Int?) =
        humidity ?: 0

    protected fun parseCloudiness(cloudiness: Int?) =
        cloudiness ?: 0

    protected fun parseRainChancePercent(rainChanceFraction: Double?) =
        (rainChanceFraction!!.times(100)).roundToInt()

    protected fun parseImageUrl(descriptions: List<Description>?): String {
        val imageId = descriptions?.get(0)?.icon ?: "errorCode"

        return "https://openweathermap.org/img/wn/${imageId}@2x.png"
    }
}