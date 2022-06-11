package com.example.stuffy.core.data.local.room

import androidx.room.TypeConverter
import com.example.stuffy.core.data.local.entity.ConfirmationEntity
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJson(value: List<ConfirmationEntity>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<ConfirmationEntity>::class.java).toList()

}