package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmationTaker(
    var image:Int,
    var name:String,
    var note:String,
): Parcelable