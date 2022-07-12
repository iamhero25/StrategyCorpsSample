package com.strategycorps.medlite.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.strategycorps.medlite.utils.Constants.Companion.CATEGORY_TABLE

@Entity(tableName = CATEGORY_TABLE)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String? = "",
    val image: String? = ""
)