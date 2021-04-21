package com.example.carrentalfinder.ui.fragments

import android.graphics.Paint
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
 * Fragment used to search for rental car and calculating the price based
 * on car specifications
 */
class SearchRentalOfferFragment : Fragment(R.layout.fragment_search_offer) {
    lateinit var viewModel: CarRentalViewModel

    private var selectedRentalCar: Car? = null
    private var selectedRentalColor: String = ""
    private var rentalPrice = 0.0
    private val basePrice: Double = 100.00

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as CarRentalActivity).carRentalViewModel

        viewModel.selectedRentalBrand.observe(viewLifecycleOwner, { rentalCar ->
            selectedRentalCar = rentalCar
            updateRentalCar(rentalCar)
            calculateRentalPrice()
        })

        viewModel.selectedRentalCarColor.observe(viewLifecycleOwner, { color ->
            selectedRentalColor = color
            updateRentalColor(color)
            calculateRentalPrice()
        })

        search_rental_add_brand.setOnClickListener {
            findNavController().navigate(R.id.action_searchOffersFragment_to_selectRentalCarFragment)
        }

        search_rental_color_add.setOnClickListener {
            findNavController().navigate(R.id.action_searchOffersFragment_to_selectRentalCarColor)
        }
    }

    /**
     * Method used to calculate price based on car weight, horsepower and selected color
     */
    private fun calculateRentalPrice() {
        if (selectedRentalCar != null) {
            rentalPrice = basePrice * selectedRentalCar!!.horsepower + selectedRentalCar!!.weight

            //If selected color is black the car will cost 15% more
            if (selectedRentalColor == "Black") {
                val newPrice = (rentalPrice * 0.15) + rentalPrice
                rentalPrice = newPrice
            //If selected color is red the car will cost 5% less
            } else if (selectedRentalColor == "Red") {
                val newPrice = rentalPrice - (rentalPrice * 0.05)
                rentalPrice = newPrice
            }
        }
        updatePrice()
    }

    //Method used to update data about selected car
    private fun updateRentalCar(rentalCar: Car) {
        search_rental_add_brand.isVisible = false
        search_rental_selected_brand_txt.text = rentalCar.brand.toUpperCase()
        search_rental_selected_brand_txt.paintFlags = search_rental_selected_brand_txt.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        search_rental_selected_horsepower_txt.text = rentalCar.horsepower.toString()
        search_rental_selected_weight_txt.text = rentalCar.weight.toString()

        search_rental_selected_brand_txt.setOnClickListener {
            findNavController().navigate(R.id.action_searchOffersFragment_to_selectRentalCarFragment)
        }
    }

    private fun updateRentalColor(color: String) {
        search_rental_color_add.isVisible = false
        search_rental_current_color_txt.text = color
        search_rental_current_color_txt.paintFlags = search_rental_current_color_txt.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        search_rental_current_color_txt.setOnClickListener {
            findNavController().navigate(R.id.action_searchOffersFragment_to_selectRentalCarColor)
        }
    }

    private fun updatePrice() {
        search_rental_current_price_txt.text = rentalPrice.toString()
    }
}