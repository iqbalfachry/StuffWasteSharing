package com.example.stuffy.core.data.local.entity

import androidx.room.*
import com.example.stuffy.core.data.remote.response.ProductResponse
import com.example.stuffy.core.data.remote.response.UserResponse

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey
    @ColumnInfo(name = "transactionId")
    val id: String,
    @Embedded
    var product: ProductEntity,
    @Embedded
    var sharer: UserEntity,

    var confirmation: List<ConfirmationEntity>,
    var status:String,
)

