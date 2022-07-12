package com.strategycorps.medlite.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.strategycorps.medlite.data.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categoryEntity: List<CategoryEntity>)

    @Query("SELECT * FROM category_table ORDER BY id ASC")
    fun getLocalCategories(): Flow<List<CategoryEntity>>

}