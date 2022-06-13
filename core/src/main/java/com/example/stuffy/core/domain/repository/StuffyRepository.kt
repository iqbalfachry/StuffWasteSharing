package com.example.stuffy.core.domain.repository

import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.*
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface StuffyRepository {

    fun getListMovie(): Flow<Resource<List<Product>>>
    fun createProduct(
        files: MultipartBody.Part,
        description: RequestBody,
        name: RequestBody,
        location: RequestBody
    ): Flow<Resource<Product>>

    fun getCategory(): Flow<Resource<List<Filter>>>
    fun getFavoriteMovie(): Flow<List<Product>>
    fun getTransactions(email: String): Flow<Resource<List<ConfirmationTransaction>>>
    fun createTransaction(
        productId: String,
        email: String,
        status: String
    ): Flow<Resource<ConfirmationTransaction>>

    fun createConfirmation(
        productId: String,
        email: String,
        status: String, note: String
    ): Flow<Resource<ConfirmationTaker>>

    fun setFavoriteMovie(movie: Product, state: Boolean)
    fun getTransactionsShare(email: String): Flow<Resource<List<Share>>>
    fun getTransactionsTake(email: String): Flow<Resource<List<Take>>>
    fun updateConfirmationStatus(
        id: String,
        status: String,
    ): Flow<Resource<ConfirmationTaker>>

    fun updateTransactionStatus(
        id: String,

        status: String
    ): Flow<Resource<ConfirmationTransaction>>
}