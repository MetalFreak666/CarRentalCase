package com.example.carrentalfinder.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrentalfinder.R
import com.example.carrentalfinder.data.models.Car
import kotlinx.android.synthetic.main.recycler_item_rental_car.view.*

/**
 * Adapter class used to display rental cars for the customer
 */
class RentalCarsAdapter() : RecyclerView.Adapter<RentalCarsAdapter.RentalCarsViewHolder>() {

    private var rentalCars: List<Car> = ArrayList()

    inner class RentalCarsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentalCarsViewHolder {
        return RentalCarsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_rental_car, parent, false))
    }

    override fun onBindViewHolder(holder: RentalCarsViewHolder, position: Int) {
        val rentalCar = rentalCars[position]

        holder.itemView.apply {
            recycler_rental_car_brand_current.text = rentalCar.brand.toUpperCase()
            recycler_rental_car_model_current.text = rentalCar.model.toUpperCase()
            recycler_rental_car_horsepower_current.text = rentalCar.horsepower.toString()
            recycler_rental_car_weight_current.text = rentalCar.weight.toString()
        }
    }

    override fun getItemCount(): Int {
        return rentalCars.size
    }

    //Submitting rental cars from previous fragment
    fun submitRentalCars(cars: List<Car>) {
        rentalCars = cars
        notifyDataSetChanged()
    }

}