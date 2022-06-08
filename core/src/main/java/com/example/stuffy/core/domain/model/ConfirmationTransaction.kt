package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmationTransaction(
    var id:String,
    var image:String,
    var name:String,
    var size:String,
    var status:String,
    var confirmation: List<ConfirmationTaker>?
        ): Parcelable
