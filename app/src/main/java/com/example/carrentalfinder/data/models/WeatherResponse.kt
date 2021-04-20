package com.example.carrentalfinder.data.models

/**
 * Data class for weather response from OpenWeather API
 * Documentation: https://openweathermap.org/current
 */
data class WeatherResponse (
    val weather: List<Weather>,
    val base: String,
    val main: List<Main>,
    val visibility: Int,
    val wind: List<Wind>
    )