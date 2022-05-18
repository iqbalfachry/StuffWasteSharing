package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    var name: String,
    var avatar: Int,
    var location: String,
    var description: String,
    val isFav: Boolean
) : Parcelable