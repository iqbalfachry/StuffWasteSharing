package com.example.stuffy.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity (
    @PrimaryKey
    @ColumnInfo(name = "userId")
    val id:String,
    val email:String,
    @ColumnInfo(name = "userAvatar")
    val avatar:String,
    @ColumnInfo(name = "userName")
    val name:String
        )