package com.strategycorps.medlite.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.strategycorps.medlite.models.Product

class ProductTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun productToString(product: Product): String {
        return gson.toJson(product)
    }

    @TypeConverter
    fun stringToProduct(data: String): Product {
        val listType = object  : TypeToken<Product>() {}.type
        return gson.fromJson(data, listType)
    }

}