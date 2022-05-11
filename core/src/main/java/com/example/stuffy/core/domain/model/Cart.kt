package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart(
    var image:Int,
    var name:String,
    var location:String,
) : Parcelable