package com.example.stuffy.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val id: Int,


    var name: String,
    var avatar: Int,
    var location: String,
    var description: String,

)