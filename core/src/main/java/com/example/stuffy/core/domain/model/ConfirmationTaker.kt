package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmationTaker(
    var id: String,
    var image: String?,
    var name:String,
    var note:String,
    var status:String,
    var email:String,
    var confirmationId:String,
): Parcelable