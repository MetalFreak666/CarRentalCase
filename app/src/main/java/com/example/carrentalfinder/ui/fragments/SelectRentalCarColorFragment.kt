package com.example.carrentalfinder.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carrentalfinder.R
import com.example.carrentalfinder.ui.CarRentalActivity
import com.example.carrentalfinder.viewmodels.CarRentalViewModel
import kotlinx.android.synthetic.main.fragment_select_rental_car_color.*

/**
 * Dialog Fragment used to selection of rental car color
 */
class SelectRentalCarColorFragment : Fragment(R.layout.fragment_select_rental_car_color) {
    lateinit var viewModel: CarRentalViewModel
    private lateinit var colorPicker: NumberPicker
    private val colors = arrayListOf("White", "Black", "Red", "Blue", "Yellow", "Silver")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_select_rental_car_color, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as CarRentalActivity).carRentalViewModel

        colorPicker = view.findViewById(R.id.select_rental_color_picker)
        colorPicker.maxValue = colors.toTypedArray().size - 1
        colorPicker.displayedValues = colors.toTypedArray()
        colorPicker.wrapSelectorWheel = true
        colorPicker.value = 0

        fab_select_rental_car_color.setOnClickListener {
            val selectedColor: String = colors[colorPicker.value]
            viewModel.updateRentalCarColor(selectedColor)

            findNavController().navigate(R.id.action_selectRentalCarColor_to_searchOffersFragment)
        }
    }
}