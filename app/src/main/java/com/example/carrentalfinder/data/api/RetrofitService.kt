package com.example.carrentalfinder.data.api

import com.example.carrentalfinder.data.api.ApiConstants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Builder class for Retrofit service
 */
class RetrofitService {

    companion object {
        private val retrofitService by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().apply {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }.build()).build()
        }
        val openWeatherAPI by lazy {
            retrofitService.create(OpenWeatherApi::class.java)
        }
    }

}