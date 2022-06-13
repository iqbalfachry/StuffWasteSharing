package com.example.stuffy.core.domain.useCase

import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.*
import com.example.stuffy.core.domain.repository.StuffyRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StuffyInteractor(private val stuffyRepository: StuffyRepository) : StuffyUseCase {
    override fun getListMovie() = stuffyRepository.getListMovie()
    override fun getCategory() =
        stuffyRepository.getCategory()


    override fun getFavMovie() = stuffyRepository.getFavoriteMovie()
    override fun createProduct(
        files: MultipartBody.Part,
        description: RequestBody,
        name: RequestBody,
        location: RequestBody
    ) =
        stuffyRepository.createProduct(files, description, name, location)

    override fun getTransactions(email: String) =
        stuffyRepository.getTransactions(email)

   override fun getTransactionsTake(email:String): Flow<Resource<List<Take>>> = stuffyRepository.getTransactionsTake(email)

    override fun setFavMovie(movie: Product, state: Boolean) =
        stuffyRepository.setFavoriteMovie(movie, state)

    override fun createTransaction(
        productId: String,
        email: String,
        status: String
    ) =stuffyRepository.createTransaction(productId,email, status)

    override fun createConfirmation(
        productId: String,
        email: String,
        status: String,
        note: String
    ): Flow<Resource<ConfirmationTaker>> =stuffyRepository.createConfirmation(productId,email, status,note)

    override fun updateConfirmationStatus(
        id: String,
        status: String
    ): Flow<Resource<ConfirmationTaker>> =stuffyRepository.updateConfirmationStatus(id, status)

    override fun updateTransactionStatus(
        id: String,
        status: String
    ): Flow<Resource<ConfirmationTransaction>> =stuffyRepository.updateTransactionStatus(id, status)

    override fun getTransactionsShare(email:String): Flow<Resource<List<Share>>> =  stuffyRepository.getTransactionsShare(email)
}