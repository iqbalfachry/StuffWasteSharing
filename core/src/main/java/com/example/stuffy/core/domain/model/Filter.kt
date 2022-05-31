package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Filter(
    var id:String,
var image:String,
var filterName:String,
) : Parcelable