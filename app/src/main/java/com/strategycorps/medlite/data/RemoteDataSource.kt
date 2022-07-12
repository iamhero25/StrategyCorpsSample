package com.strategycorps.medlite.data

import com.strategycorps.medlite.data.entities.CategoryEntity
import com.strategycorps.medlite.data.entities.ProductEntity
import com.strategycorps.medlite.data.network.ProductsApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val productsApi: ProductsApi
) {

    suspend fun getProducts(): List<ProductEntity>{
       return productsApi.getProductList()
    }

    suspend fun getCategories(): List<CategoryEntity>{
        return productsApi.getCategoryList()
    }

}