package com.example.carrentalfinder.utils

import android.content.Context
import java.io.IOException

/**
 * Helper function used to open and read json file from assets
 */
fun getJsonDataFromAssets(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}