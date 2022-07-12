package com.strategycorps.medlite.data.network

import com.strategycorps.medlite.data.entities.CategoryEntity
import com.strategycorps.medlite.data.entities.ProductEntity
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    suspend fun getProductList(): List<ProductEntity>

    @GET("categories")
    suspend fun getCategoryList(): List<CategoryEntity>

}