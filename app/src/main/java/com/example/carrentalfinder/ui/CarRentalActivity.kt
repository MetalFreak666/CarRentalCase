package com.example.carrentalfinder.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.carrentalfinder.R
import com.example.carrentalfinder.data.models.Car
import com.example.carrentalfinder.repositories.WeatherRepository
import com.example.carrentalfinder.utils.getJsonDataFromAssets
import com.example.carrentalfinder.viewmodels.CarRentalViewModel
import com.example.carrentalfinder.viewmodels.CarRentalViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber

/**
 * Car rental activity
 */
class CarRentalActivity : AppCompatActivity() {
    lateinit var carRentalViewModel: CarRentalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_rental)

        //Setup of TimberLogger for debugging
        Timber.plant(Timber.DebugTree())

        val repository = WeatherRepository()
        val viewModelProvider = CarRentalViewModelProvider(repository)
        carRentalViewModel = ViewModelProvider(this, viewModelProvider).get(CarRentalViewModel::class.java)

    }
}