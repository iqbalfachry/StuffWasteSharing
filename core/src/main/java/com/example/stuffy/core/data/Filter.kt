package com.example.stuffy.core.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Filter(
var image:Int,
var filterName:String,
) : Parcelable