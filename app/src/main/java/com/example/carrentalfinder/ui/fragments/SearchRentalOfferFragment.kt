package com.example.carrentalfinder.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.carrentalfinder.R
import com.example.carrentalfinder.data.models.Car
import com.example.carrentalfinder.ui.CarRentalActivity
import com.example.carrentalfinder.utils.Resource
import com.example.carrentalfinder.utils.getJsonDataFromAssets
import com.example.carrentalfinder.viewmodels.CarRentalViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber

class SearchRentalOfferFragment : Fragment(R.layout.fragment_search_offer) {
    lateinit var viewModel: CarRentalViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as CarRentalActivity).carRentalViewModel

        viewModel.currentWeather.observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { currentWeather ->
                        Timber.i("GG $currentWeather")
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

        val gson = Gson()
        val jsonFileString = getJsonDataFromAssets(this.requireContext(), "cars.json")
        Timber.i("GG $jsonFileString")

        val listRentalCars = object : TypeToken<List<Car>>(){}.type

        var rentalCars: List<Car> = gson.fromJson(jsonFileString, listRentalCars)
        Timber.i("GG2 ${rentalCars[101]}")
    }
}