package com.strategycorps.medlite.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.strategycorps.medlite.data.entities.CategoryEntity
import com.strategycorps.medlite.data.entities.ProductEntity
import com.strategycorps.medlite.data.converters.CategoryTypeConverter
import com.strategycorps.medlite.data.converters.ProductTypeConverter
import com.strategycorps.medlite.dao.CategoryDao
import com.strategycorps.medlite.dao.ProductDao

@Database(entities = [ProductEntity::class, CategoryEntity::class], version = 1, exportSchema = false)

@TypeConverters(ProductTypeConverter::class, CategoryTypeConverter::class)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao

}