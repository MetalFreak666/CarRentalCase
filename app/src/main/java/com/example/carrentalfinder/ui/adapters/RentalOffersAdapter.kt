package com.example.carrentalfinder.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrentalfinder.R
import com.example.carrentalfinder.data.models.RentalOffer
import kotlinx.android.synthetic.main.recycler_item_rental_offer.view.*

/**
 * Adapter class used to display rental offers
 */
class RentalOffersAdapter() : RecyclerView.Adapter<RentalOffersAdapter.RentalOffersViewHolder>() {

    private var rentalOffers: List<RentalOffer> = ArrayList()
    private var onItemClickListener: ((RentalOffer) -> Unit)? = null

    inner class RentalOffersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentalOffersViewHolder {
        return RentalOffersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_rental_offer, parent, false))
    }

    override fun onBindViewHolder(holder: RentalOffersViewHolder, position: Int) {
        val rentalOffer = rentalOffers[position]

        //Binding rental offer data to the ItemView
        holder.itemView.apply {
            recycler_car_brand_txt.text = rentalOffer.car.brand.toUpperCase()
            recycler_car_model_txt.text = rentalOffer.car.model.toUpperCase()
            recycler_car_year_txt.text = rentalOffer.car.year
            recycler_rental_price_txt.text = rentalOffer.price.toString()

            setOnClickListener{
                onItemClickListener?.let { it(rentalOffer) }
            }
        }
    }

    override fun getItemCount(): Int {
        return rentalOffers.size
    }

    //Submitting rental offers from host fragment
    fun submitOffers(offers: List<RentalOffer>) {
        rentalOffers = offers
        notifyDataSetChanged()
    }

    //Called when user selected rental offer from RecyclerView
    fun setOnClickListener(listener: (RentalOffer) -> Unit) {
        onItemClickListener = listener
    }
}