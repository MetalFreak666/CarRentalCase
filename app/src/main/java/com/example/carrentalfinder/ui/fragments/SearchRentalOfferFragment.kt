package com.example.carrentalfinder.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carrentalfinder.R
import com.example.carrentalfinder.ui.CarRentalActivity
import com.example.carrentalfinder.viewmodels.CarRentalViewModel
import kotlinx.android.synthetic.main.fragment_search_offer.*

class SearchRentalOfferFragment : Fragment(R.layout.fragment_search_offer) {
    lateinit var viewModel: CarRentalViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as CarRentalActivity).carRentalViewModel


        search_rental_add_brand.setOnClickListener {
            findNavController().navigate(R.id.action_searchOffersFragment_to_selectRentalCarFragment)
        }
    }
}