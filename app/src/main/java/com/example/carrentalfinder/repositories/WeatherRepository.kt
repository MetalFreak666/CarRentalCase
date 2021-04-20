package com.example.carrentalfinder.repositories

import com.example.carrentalfinder.data.api.RetrofitService

/**
 * Repository class used for accessing remote data from OpenWeatherAPI
 */
class WeatherRepository {

    suspend fun getWeather(city: String) = RetrofitService.openWeatherAPI.getWeather()

}