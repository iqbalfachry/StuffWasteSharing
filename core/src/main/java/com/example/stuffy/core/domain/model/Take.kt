package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Take(
    var image:Int,
    var name:String,
    var location:String,
    var status:String,
) : Parcelable