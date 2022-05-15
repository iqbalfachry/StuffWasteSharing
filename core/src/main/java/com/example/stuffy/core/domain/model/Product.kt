package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    var name: String,
    var avatar: Int,
    var location: String,
    var description: String,
) : Parcelable