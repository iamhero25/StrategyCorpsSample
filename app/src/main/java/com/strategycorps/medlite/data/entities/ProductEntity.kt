package com.strategycorps.medlite.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.strategycorps.medlite.models.Product
import com.strategycorps.medlite.utils.Constants.Companion.PRODUCT_TABLE

@Entity(tableName = PRODUCT_TABLE)
class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val image: String,
    val originalPrice: Int,
    val price: Int,
    val unit: String,
    val description: String,
    val category: String?
)