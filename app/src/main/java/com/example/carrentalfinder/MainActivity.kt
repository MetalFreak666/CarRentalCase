package com.example.carrentalfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carrentalfinder.repositories.WeatherRepository
import com.example.carrentalfinder.utils.Resource
import com.example.carrentalfinder.viewmodels.CarRentalViewModel
import com.example.carrentalfinder.viewmodels.CarRentalViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var carRentalViewModel: CarRentalViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setup of TimberLogger
        Timber.plant(Timber.DebugTree())

        val repository = WeatherRepository()
        val viewModelProvider = CarRentalViewModelProvider(repository)
        carRentalViewModel = ViewModelProvider(this, viewModelProvider).get(CarRentalViewModel::class.java)

        carRentalViewModel.currentWeather.observe(this, { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { currentWeather ->
                        test.text = currentWeather.toString()
                    }
                }
                is Resource.Loading -> {
                    Timber.i("Loading weather data")
                }
                is Resource.Error -> {
                    response.message?.let { errorMessage ->
                        Timber.d("Error: $errorMessage")
                    }
                }
            }
        })


    }
}