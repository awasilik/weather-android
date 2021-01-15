package com.elke.weather.util

import com.elke.weather.domain.model.Location

interface LocationStorage {
    fun save(location: Location)

    fun retrieve(): Location
}