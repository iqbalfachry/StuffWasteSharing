package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Take(
    var id:String,
    var image:String,
    var name:String,
    var location:String,
    var status:List<ConfirmationTaker>?,

) : Parcelable