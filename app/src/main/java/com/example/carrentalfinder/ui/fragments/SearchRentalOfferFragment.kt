package com.example.carrentalfinder.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carrentalfinder.R
import com.example.carrentalfinder.data.models.Car
import com.example.carrentalfinder.ui.CarRentalActivity
import com.example.carrentalfinder.viewmodels.CarRentalViewModel
import kotlinx.android.synthetic.main.fragment_search_offer.*

/**
 * Fragment used to search for rental car
 */
class SearchRentalOfferFragment : Fragment(R.layout.fragment_search_offer) {
    lateinit var viewModel: CarRentalViewModel
    private val basePrice = 100

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as CarRentalActivity).carRentalViewModel
        viewModel.selectedRentalBrand.observe(viewLifecycleOwner, { rentalCar ->
            updateRentalCar(rentalCar)
            calculateRentalPrice(rentalCar)
        })

        search_rental_add_brand.setOnClickListener {
            findNavController().navigate(R.id.action_searchOffersFragment_to_selectRentalCarFragment)
        }
    }

    //Method used to update data about selected car
    private fun updateRentalCar(rentalCar: Car) {
        search_rental_add_brand.isVisible = false
        search_rental_selected_brand_txt.text = rentalCar.brand.toUpperCase()
        search_rental_selected_horsepower_txt.text = rentalCar.horsepower.toString()
        search_rental_selected_weight_txt.text = rentalCar.weight.toString()

        search_rental_selected_brand_txt.setOnClickListener {
            findNavController().navigate(R.id.action_searchOffersFragment_to_selectRentalCarFragment)
        }
    }

    private fun calculateRentalPrice(rentalCar: Car) {
        val rentalPrice = basePrice * rentalCar.horsepower + rentalCar.weight
        search_rental_current_price_txt.text = rentalPrice.toString()
    }
}