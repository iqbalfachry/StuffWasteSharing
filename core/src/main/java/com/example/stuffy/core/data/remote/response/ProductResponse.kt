package com.example.stuffy.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(

    var id: String,
    var name: String,
    var avatar: String?=null,
    var location: String,
    var description: String,

)