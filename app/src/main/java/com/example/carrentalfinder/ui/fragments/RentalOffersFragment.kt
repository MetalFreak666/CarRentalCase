package com.example.carrentalfinder.ui.fragments

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrentalfinder.R
import com.example.carrentalfinder.data.models.Car
import com.example.carrentalfinder.data.models.RentalOffer
import com.example.carrentalfinder.data.models.WeatherResponse
import com.example.carrentalfinder.ui.CarRentalActivity
import com.example.carrentalfinder.ui.adapters.RentalOffersAdapter
import com.example.carrentalfinder.utils.Resource
import com.example.carrentalfinder.utils.getJsonDataFromAssets
import com.example.carrentalfinder.viewmodels.CarRentalViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_rental_offers.*
import timber.log.Timber

/**
 * Fragment used to display selected car rental offer
 */
class RentalOffersFragment : Fragment(R.layout.fragment_rental_offers) {
    lateinit var viewModel: CarRentalViewModel
    lateinit var rentalOffersAdapter: RentalOffersAdapter
    private var rentalCars: List<Car> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as CarRentalActivity).carRentalViewModel

        viewModel.currentWeather.observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { currentWeather ->
                        displayWeatherConditions(currentWeather)
                        calculatePrice(currentWeather)
                        rental_offers_progress_bar.isVisible = false
                    }
                }
                is Resource.Loading -> {
                    Timber.i("Loading weather data")
                    rental_offers_progress_bar.isVisible = true
                }
                is Resource.Error -> {
                    response.message?.let { errorMessage ->
                        Timber.d("Error: $errorMessage")
                    }
                }
            }
        })

        getRentalCars()
        setupRecyclerView()
    }

    //Getting cars from JSON file in assets directory
    private fun getRentalCars() {
        val gson = Gson()
        val jsonFileString = getJsonDataFromAssets(this.requireContext(), "cars.json")
        val listRentalCars = object : TypeToken<List<Car>>(){}.type

        rentalCars = gson.fromJson(jsonFileString, listRentalCars)
    }

    /**
     * Method used to calculate price based on weather conditions
     */
    private fun calculatePrice(currentWeather: WeatherResponse) {
        val basePrice = 200.00
        val rentalCarsOffers: MutableList<RentalOffer> = mutableListOf()

        for (car in rentalCars) {
            val rentalPrice = basePrice + car.horsepower

            if (currentWeather.wind.speed > 10.00) {
                rentalPrice + 100.99
                rentalCarsOffers.add(RentalOffer(car, rentalPrice))
            } else {
                rentalCarsOffers.add(RentalOffer(car, rentalPrice))
            }
        }

        submitOffers(rentalCarsOffers)
    }

    private fun setupRecyclerView() {
        rentalOffersAdapter = RentalOffersAdapter()
        rental_offers_recycler_view.apply {
            adapter = rentalOffersAdapter
            layoutManager = LinearLayoutManager(activity)
            val spacingDecorator = DividerItemDecoration(context, ClipDrawable.HORIZONTAL)
            addItemDecoration(spacingDecorator)
        }
    }

    private fun submitOffers(rentalOffers: List<RentalOffer>) {
        rentalOffersAdapter.submitOffers(rentalOffers)
    }

    //Displaying current weather conditions in UI elements
    private fun displayWeatherConditions(currentWeather: WeatherResponse) {
        rental_offers_current_location_txt.text = currentWeather.name
        rental_offers_current_temperature_txt.text = currentWeather.main.temp.toString()
        rental_offers_current_wind_txt.text = currentWeather.wind.speed.toString()
    }
}