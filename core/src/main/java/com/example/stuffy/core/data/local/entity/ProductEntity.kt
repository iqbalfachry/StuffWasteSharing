package com.example.stuffy.core.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    val id: String,
    var name: String,
    var avatar: String,
    var location: String,
    var description: String,

    var isFav: Boolean = false
)