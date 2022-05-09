package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Filter(
var image:Int,
var filterName:String,
) : Parcelable