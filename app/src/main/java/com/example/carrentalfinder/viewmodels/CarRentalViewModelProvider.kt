package com.example.carrentalfinder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.carrentalfinder.repositories.WeatherRepository

class CarRentalViewModelProvider(private val repository: WeatherRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarRentalViewModel(repository) as T
    }

}