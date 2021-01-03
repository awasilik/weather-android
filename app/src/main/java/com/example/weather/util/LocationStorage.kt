package com.example.weather.util

import com.example.weather.domain.model.Location

interface LocationStorage {
    fun save(location: Location)

    fun retrieve(): Location
}