package com.elke.weather.domain.location

interface LocationStorage {
    fun save(location: Location)

    fun retrieve(): Location
}