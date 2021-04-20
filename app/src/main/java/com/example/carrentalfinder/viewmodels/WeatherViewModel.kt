package com.example.carrentalfinder.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrentalfinder.data.models.WeatherResponse
import com.example.carrentalfinder.repositories.WeatherRepository
import com.example.carrentalfinder.utils.Resource

/**
 * ViewModel class for weather live data
 */
class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    val currentWeather: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()
    val city = "reykjavik"

}