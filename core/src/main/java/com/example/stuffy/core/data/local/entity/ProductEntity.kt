package com.example.stuffy.core.data.local.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "productId")
    val id: String,
    @ColumnInfo(name = "productName")
    var name: String,
    @ColumnInfo(name = "productAvatar")
    var avatar: String,
    var location: String,
    var description: String,

    var isFav: Boolean = false
)