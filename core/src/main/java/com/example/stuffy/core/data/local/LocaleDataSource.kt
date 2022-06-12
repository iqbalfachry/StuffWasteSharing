package com.example.stuffy.core.data.local

import com.example.stuffy.core.data.local.entity.ConfirmationEntity
import com.example.stuffy.core.data.local.entity.ProductEntity
import com.example.stuffy.core.data.local.entity.TransactionEntity
import com.example.stuffy.core.data.local.room.ProductDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class LocalDataSource(private val productDao: ProductDao) {


    fun getListProduct(): Flow<List<ProductEntity>> = productDao.getListProduct()
    fun getListTransaction(): Flow<List<TransactionEntity>> = productDao.getListTransaction()
    fun getListTransactionById(email:String): Flow<List<TransactionEntity>> = productDao.getListTransactionById(email)
    fun getListConfirmation(): Flow<List<ConfirmationEntity>> = productDao.getListConfirmation()
    fun getFavMovie(): Flow<List<ProductEntity>> = productDao.getFavMovie()

    suspend fun insertProduct(productList: List<ProductEntity>) = productDao.insertProduct(productList)

    suspend fun insertTransaction(transactionList: List<TransactionEntity>) = productDao.insertTransaction(transactionList)
    suspend fun insertConfirmation(confirmationList: List<ConfirmationEntity>) = productDao.insertConfirmation(confirmationList)
    fun setFavMovie(movie: ProductEntity, state: Boolean) {
        movie.isFav = state
        productDao.setFavMovie(movie)
    }
}