package com.example.carrentalfinder.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.carrentalfinder.R
import com.example.carrentalfinder.repositories.WeatherRepository
import com.example.carrentalfinder.viewmodels.CarRentalViewModel
import com.example.carrentalfinder.viewmodels.CarRentalViewModelProvider
import kotlinx.android.synthetic.main.activity_car_rental.*
import timber.log.Timber

/**
 * Car rental activity
 */
class CarRentalActivity : AppCompatActivity() {
    lateinit var carRentalViewModel: CarRentalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_rental)

        navigation_menu.setupWithNavController(rentalNavHostFragment.findNavController())

        //Setup of TimberLogger for debugging
        Timber.plant(Timber.DebugTree())

        val repository = WeatherRepository()
        val viewModelProvider = CarRentalViewModelProvider(repository)
        carRentalViewModel = ViewModelProvider(this, viewModelProvider).get(CarRentalViewModel::class.java)
    }
}