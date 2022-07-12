package com.strategycorps.medlite.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: String?,
    val title: String?,
    val image: String?,
    val originalPrice: String?,
    val price: String?,
    val unit: String?,
    val description: String?,
    val category: String?
) : Parcelable