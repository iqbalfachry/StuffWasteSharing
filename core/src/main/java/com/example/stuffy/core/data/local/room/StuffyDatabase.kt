package com.example.stuffy.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stuffy.core.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class StuffyDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao


}