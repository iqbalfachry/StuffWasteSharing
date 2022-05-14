package com.example.stuffy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    var username: String,
    var name: String,
    var avatar: Int,
    var follower: String,
    var following: String,
    var company: String,
    var location: String,
    var repository: String,
) : Parcelable