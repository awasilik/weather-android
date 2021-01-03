package com.example.weather.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreverencesHelper @Inject constructor(@ApplicationContext private val context: Context) {
    val key = "weather_app"

    val sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
}