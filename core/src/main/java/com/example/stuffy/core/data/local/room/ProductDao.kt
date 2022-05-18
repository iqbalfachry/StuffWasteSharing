package com.example.stuffy.core.data.local.room

import androidx.room.*
import com.example.stuffy.core.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getListMovie(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE isFav = 1")
    fun getFavMovie(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<ProductEntity>)

    @Update
    fun setFavMovie(movie: ProductEntity)
}