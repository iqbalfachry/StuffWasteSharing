package com.example.stuffy.core.data.local.room

import androidx.room.*
import com.example.stuffy.core.data.local.entity.ConfirmationEntity
import com.example.stuffy.core.data.local.entity.ProductEntity
import com.example.stuffy.core.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getListProduct(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM `transaction`")
    fun getListTransaction(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM confirmation")
    fun getListConfirmation(): Flow<List<ConfirmationEntity>>

    @Query("SELECT * FROM product WHERE isFav = 1")
    fun getFavMovie(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(movie: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(movie: List<TransactionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfirmation(movie: List<ConfirmationEntity>)

    @Update
    fun setFavMovie(movie: ProductEntity)
}