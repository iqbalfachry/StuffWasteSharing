package com.example.stuffy.core.domain.useCase


import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.*
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface StuffyUseCase {
    fun getListMovie(): Flow<Resource<List<Product>>>
    fun getCategory(): Flow<Resource<List<Filter>>>
    fun getFavMovie(): Flow<List<Product>>
    fun createProduct(files: MultipartBody.Part, description: RequestBody, name: RequestBody, location: RequestBody): Flow<Resource<Product>>
    fun getTransactions(email: String): Flow<Resource<List<ConfirmationTransaction>>>
    fun getTransactionsShare(email:String): Flow<Resource<List<Share>>>
    fun getTransactionsTake(email:String): Flow<Resource<List<Take>>>
    fun setFavMovie(movie: Product, state: Boolean)
    fun createTransaction(productId: String,
                          email: String,
                          status: String): Flow<Resource<ConfirmationTransaction>>
    fun createConfirmation(productId: String,
                           email: String,
                           status: String,note:String): Flow<Resource<ConfirmationTaker>>
    fun updateConfirmationStatus( id: String,
                                  status: String,): Flow<Resource<ConfirmationTaker>>
    fun updateTransactionStatus(
        id: String,

        status: String
    ): Flow<Resource<ConfirmationTransaction>>
}