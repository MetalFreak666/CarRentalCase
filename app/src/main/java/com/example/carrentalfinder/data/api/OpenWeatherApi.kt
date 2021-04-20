package com.example.carrentalfinder.data.api

import com.example.carrentalfinder.data.api.ApiConstants.Companion.API_KEY
import com.example.carrentalfinder.data.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface used to getting weather from OpenWeather API
 * Documentation: https://openweathermap.org/current
 */
interface OpenWeatherApi {

    //Used to getting current weather from city
    @GET("data/2.5/weather?")
    suspend fun getWeather(
        @Query("city name")
        city: String,
        @Query("appid")
        apiKey: String = API_KEY
    ): Response<WeatherResponse>
}