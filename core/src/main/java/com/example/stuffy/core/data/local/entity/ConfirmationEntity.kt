package com.example.stuffy.core.data.local.entity

import androidx.room.*
import com.example.stuffy.core.data.remote.response.UserResponse

@Entity(tableName = "confirmation")
data class ConfirmationEntity (
@PrimaryKey
@ColumnInfo(name = "confirmationId")
    val id: String,
    val note:String,
    val status:String,
@Embedded
    val taker: UserEntity,

)