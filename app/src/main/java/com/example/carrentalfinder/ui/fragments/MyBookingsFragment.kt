package com.example.carrentalfinder.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.carrentalfinder.R
import com.example.carrentalfinder.ui.CarRentalActivity
import com.example.carrentalfinder.viewmodels.CarRentalViewModel

/**
 * Fragment used to display user bookings
 */
class MyBookingsFragment : Fragment(R.layout.fragment_bookings) {
    lateinit var viewModel: CarRentalViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as CarRentalActivity).carRentalViewModel
    }
}