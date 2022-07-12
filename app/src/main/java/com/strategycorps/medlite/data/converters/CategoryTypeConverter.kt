package com.strategycorps.medlite.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.strategycorps.medlite.models.CategoryModel

class CategoryTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun categoryToString(product: CategoryModel): String {
        return gson.toJson(product)
    }

    @TypeConverter
    fun categoryToProduct(data: String): CategoryModel {
        val listType = object  : TypeToken<CategoryModel>() {}.type
        return gson.fromJson(data, listType)
    }

}