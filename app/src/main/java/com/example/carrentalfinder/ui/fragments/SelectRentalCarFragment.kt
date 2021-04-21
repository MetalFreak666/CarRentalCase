package com.example.carrentalfinder.ui.fragments

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrentalfinder.R
import com.example.carrentalfinder.data.models.Car
import com.example.carrentalfinder.ui.CarRentalActivity
import com.example.carrentalfinder.ui.adapters.RentalCarsAdapter
import com.example.carrentalfinder.utils.getJsonDataFromAssets
import com.example.carrentalfinder.viewmodels.CarRentalViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_select_rental_car.*
import timber.log.Timber

/**
 * Fragment used to selection of rental car
 */
class SelectRentalCarFragment : Fragment(R.layout.fragment_select_rental_car) {
    lateinit var viewModel: CarRentalViewModel
    lateinit var rentalCarsAdapter: RentalCarsAdapter
    private var rentalCars: List<Car> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as CarRentalActivity).carRentalViewModel

        setupRecyclerView()
        getRentalCars()

        rentalCarsAdapter.setOnClickListener {
            viewModel.updateCar(it)

            findNavController().navigate(R.id.action_selectRentalCarFragment_to_searchOffersFragment)
        }
    }

    //Getting cars from JSON file in assets directory
    private fun getRentalCars() {
        val gson = Gson()
        val jsonFileString = getJsonDataFromAssets(this.requireContext(), "cars.json")
        val listRentalCars = object : TypeToken<List<Car>>(){}.type

        rentalCars = gson.fromJson(jsonFileString, listRentalCars)
        submitRentalCars(rentalCars)
    }

    private fun setupRecyclerView() {
        rentalCarsAdapter = RentalCarsAdapter()
        select_rental_car_recycler.apply {
            adapter = rentalCarsAdapter
            layoutManager = LinearLayoutManager(activity)
            val spacingDecorator = DividerItemDecoration(context, ClipDrawable.HORIZONTAL)
            addItemDecoration(spacingDecorator)
        }

    }

    private fun submitRentalCars(rentalCars: List<Car>) {
        rentalCarsAdapter.submitRentalCars(rentalCars)
    }
}