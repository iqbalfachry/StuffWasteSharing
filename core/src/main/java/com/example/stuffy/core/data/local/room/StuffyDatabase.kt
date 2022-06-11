package com.example.stuffy.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.stuffy.core.data.local.entity.ConfirmationEntity
import com.example.stuffy.core.data.local.entity.ProductEntity
import com.example.stuffy.core.data.local.entity.TransactionEntity
import com.example.stuffy.core.data.local.entity.UserEntity

@Database(entities = [ProductEntity::class,TransactionEntity::class,ConfirmationEntity::class, UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class StuffyDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao


}