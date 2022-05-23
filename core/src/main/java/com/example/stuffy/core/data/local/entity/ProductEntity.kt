package com.example.stuffy.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    @NonNull
    val id: String,
    var name: String,
    var avatar: String,
    var location: String,
    var description: String,

    var isFav: Boolean = false
)