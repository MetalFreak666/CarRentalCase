package com.example.carrentalfinder.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.example.carrentalfinder.R
import com.example.carrentalfinder.data.models.Car
import com.example.carrentalfinder.utils.getJsonDataFromAssets
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Dialog fragment used to select brand of the rental car
 */
class SelectBrandFragment : DialogFragment() {
    private lateinit var carBrandPicker: NumberPicker
    private var rentalCars: List<Car> = ArrayList()
    private var brands: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getRentalCars()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_select_brand, container, false)
        carBrandPicker = view.findViewById(R.id.select_brand_picker)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carBrandPicker.maxValue = brands.toTypedArray().size - 1
        carBrandPicker.displayedValues = brands.toTypedArray()
        carBrandPicker.wrapSelectorWheel = true
        carBrandPicker.value = 0
    }

    //This method is used to set the rounded edges for the Dialog Fragment layout
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(R.drawable.rounded_layout_edges)

        return dialog
    }

    //Getting cars from Json file
    private fun getRentalCars() {
        val gson = Gson()
        val jsonFileString = getJsonDataFromAssets(this.requireContext(), "cars.json")
        val listRentalCars = object : TypeToken<List<Car>>(){}.type

        rentalCars = gson.fromJson(jsonFileString, listRentalCars)

        for (cars in rentalCars) {
            brands.add(cars.brand.toUpperCase())
        }
    }
}