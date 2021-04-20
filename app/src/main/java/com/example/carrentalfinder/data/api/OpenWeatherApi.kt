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
    @GET("https://api.openweathermap.org/data/2.5/weather")
    suspend fun getWeather(
        @Query("q")
        city: String = "reykjavik",
        @Query("appid")
        app_id: String = API_KEY
    ): Response<WeatherResponse>
}