package com.elke.weather.domain.location

import android.content.Context
import com.elke.weather.R

enum class Location(val longitude: Double, val latitude: Double, val labelId: Int) {
    Warsaw(21.01, 52.22, R.string.city_warsaw),
    Cracow(19.91, 50.08, R.string.city_cracow),
    Tarnobrzeg(21.69, 50.58, R.string.city_tarnobrzeg),
    London(-0.09, 51.51, R.string.city_london),
    Berlin(10.45, 54.03, R.string.city_berlin),
    Madrid(-3.70, 40.41, R.string.city_madrid),
    Paris(2.35, 48.85, R.string.city_paris),
    Prague(14.42, 50.08, R.string.city_prague),
    Moers(6.65, 51.45, R.string.city_moers)
}

fun Location.getName(context: Context) =
    context.getString(this.labelId)