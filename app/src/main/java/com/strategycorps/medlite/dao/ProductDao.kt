package com.strategycorps.medlite.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.strategycorps.medlite.data.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(productEntity: List<ProductEntity>)

    @Query("SELECT * FROM product_table ORDER BY id ASC")
    fun readProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product_table WHERE category LIKE :search")
    fun getProductsByCategory(search: String): Flow<List<ProductEntity>>

}