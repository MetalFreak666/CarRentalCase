package com.example.carrentalfinder.ui.fragments

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
                        getRentalCars()
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

        setupRecyclerView()

        rentalOffersAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("rentalOffer", it)
            }

            //Using navigation controller to parsing selected rental offer data to the new fragment
            findNavController().navigate(R.id.action_rentalOffersFragment_to_rentalDetailFragment, bundle)
        }
    }

    //Getting cars from JSON file in assets directory
    private fun getRentalCars() {
        val gson = Gson()
        val jsonFileString = getJsonDataFromAssets(this.requireContext(), "cars.json")
        val listRentalCars = object : TypeToken<List<Car>>(){}.type

        rentalCars = gson.fromJson(jsonFileString, listRentalCars)
    }

    private fun calculatePrice(currentWeather: WeatherResponse) {
        //Displaying current weather conditions in UI elements
        rental_offers_location.text = currentWeather.name
        rental_offers_temperature.text = currentWeather.main.temp.toString()
        rental_offers_wind_condition.text = currentWeather.wind.speed.toString()

        val basePrice = 200
        val rentalCarsOffers: MutableList<RentalOffer> = mutableListOf()

        for (car in rentalCars) {
            if (car.year.toInt() > 1975) {
                val rentalPrice = basePrice + currentWeather.wind.speed + currentWeather.main.temp
                rentalCarsOffers.add(RentalOffer(car, rentalPrice))
            } else {
                val rentalPrice = basePrice + currentWeather.wind.speed + currentWeather.main.temp * 2
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
}