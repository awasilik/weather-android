package com.elke.weather.domain.dataProcessors

import java.time.Instant
import java.time.ZoneId
import kotlin.math.roundToInt

abstract class DataProcessor<Input, Output> {
    abstract fun process(apiData: Input) : Output

    protected fun parseRainChancePercent(rainChanceFraction: Double?) =
        (rainChanceFraction!!.times(100)).roundToInt()

    protected fun parseTemperature(temperature: Double?) =
        temperature!!.roundToInt()

    protected fun parseImageUrl(imageId: String?) =
        "https://openweathermap.org/img/wn/${imageId}@2x.png"

    protected fun parseTime(timeStamp: Long?) =
        Instant.ofEpochSecond(timeStamp!!).atZone(ZoneId.systemDefault()).toLocalDateTime()
}