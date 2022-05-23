package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String,
    var name: String,
    var avatar: String?=null,
    var location: String,
    var description: String,
    val isFav: Boolean
) : Parcelable