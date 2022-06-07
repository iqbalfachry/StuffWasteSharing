package com.example.stuffy.core.data.remote.response

data class TransactionResponse(
    var id: String,
    var product:ProductResponse,
    var sharer:UserResponse,
    var confirmation:List<ConfirmationResponse>,
    var status:String,
)
