package com.example.carrentalfinder.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrentalfinder.data.models.WeatherResponse
import com.example.carrentalfinder.repositories.WeatherRepository
import com.example.carrentalfinder.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * ViewModel class for weather live data
 */
class CarRentalViewModel(private val repository: WeatherRepository) : ViewModel() {
    val currentWeather: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()
    private val city = "reykjavik"

    init {
        getCurrentWeather(city)
    }

    private fun getCurrentWeather(city: String) = viewModelScope.launch {
        currentWeather.postValue(Resource.Loading())
        val response = repository.getWeather(city)
        currentWeather.postValue(responseHandler(response))

    }

    private fun responseHandler(response: Response<WeatherResponse>) : Resource<WeatherResponse> {
        if (response.isSuccessful) {
            response.body()?.let { results ->
                return Resource.Success(results)
            }
        }
        return Resource.Error(response.message())
    }
}