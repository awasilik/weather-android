package com.elke.weather.util

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Preferences @Inject constructor(@ApplicationContext private val context: Context){
    private val key = "weather_app"

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(key, Context.MODE_PRIVATE)

    fun getString(key: String, default: String = "") =
        sharedPreferences.getString(key, default)

    fun putString(key: String, value: String) {
        sharedPreferences.edit().remove(key).putString(key, value).apply()
    }
}