package com.strategycorps.medlite.data

import com.strategycorps.medlite.data.entities.CategoryEntity
import com.strategycorps.medlite.data.entities.ProductEntity
import com.strategycorps.medlite.dao.CategoryDao
import com.strategycorps.medlite.dao.ProductDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val productDao: ProductDao, private  val categoryDao: CategoryDao) {

    //PRODUCTS
    suspend fun insertProducts(productEntity: List<ProductEntity>){
        productDao.insertProducts(productEntity)
    }

    fun readProducts(): Flow<List<ProductEntity>> {
        return productDao.readProducts()
    }

    fun getProductsByCategory(search: String): Flow<List<ProductEntity>> {
        return productDao.getProductsByCategory(search)
    }


    //CATEGORIES
    suspend fun insertCategories(categoryEntity: List<CategoryEntity>){
        categoryDao.insertCategories(categoryEntity)
    }

    fun getLocalCategories(): Flow<List<CategoryEntity>> {
        return categoryDao.getLocalCategories()
    }

}