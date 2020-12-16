package com.example.weather.domain

enum class Location(val city: String, val country: String) {
    WARSAW("Warszawa", "pl"),
    CRACOW("Kraków", "pl"),
    TARNOBRZEG("Tarnobrzeg", "pl"),
    LONDON("Londyn", "uk"),
    BERLIN("Berlin", "de"),
    MADRID("Madryt", "es"),
    PARIS("Paryż", "fr"),
    PRAGUE("Praga", "cz"),
}