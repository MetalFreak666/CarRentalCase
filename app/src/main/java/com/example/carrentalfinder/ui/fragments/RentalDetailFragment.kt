package com.example.carrentalfinder.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.carrentalfinder.R
import com.example.carrentalfinder.ui.CarRentalActivity
import com.example.carrentalfinder.viewmodels.CarRentalViewModel
import kotlinx.android.synthetic.main.fragment_rental_detail.*

/**
 * Fragment used to display details about selected rental offer
 */
class RentalDetailFragment : Fragment(R.layout.fragment_rental_detail) {
    lateinit var viewModel: CarRentalViewModel
    private val args: RentalDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as CarRentalActivity).carRentalViewModel
        val selectedRentalOffer = args.rentalOffer

        rental_detail_car_brand.text = selectedRentalOffer.car.brand
    }
}