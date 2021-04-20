package com.example.carrentalfinder.data.models

/**
 * Data class for main entity
 */
data class Main (
    val temp: Int,
    val feels_like: Int,
    val temp_min: Int,
    val temp_max: Int,
    val pressure: Int,
    val humidity: Int
    )