package com.example.stuffy.core.data.remote.response

data class ConfirmationResponse(
    val id:String,
    val note:String,
    val status:String,
    val taker:UserResponse,
)
