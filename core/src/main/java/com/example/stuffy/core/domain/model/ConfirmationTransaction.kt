package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmationTransaction (
    var image:Int,
    var name:String,
    var size:String,
        ): Parcelable
