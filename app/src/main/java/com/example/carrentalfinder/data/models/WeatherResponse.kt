package com.example.carrentalfinder.data.models

/**
 * Data class for weather response from OpenWeather API
 * Documentation: https://openweathermap.org/current
 */
data class WeatherResponse (
    val coord: Coordinates,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val name: String
    )