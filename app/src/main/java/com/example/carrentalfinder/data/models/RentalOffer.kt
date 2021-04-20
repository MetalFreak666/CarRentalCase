package com.example.carrentalfinder.data.models

import java.io.Serializable

/**
 * Data class for rental offer
 */
data class RentalOffer (
    val car: Car,
    val price: Double
    ): Serializable