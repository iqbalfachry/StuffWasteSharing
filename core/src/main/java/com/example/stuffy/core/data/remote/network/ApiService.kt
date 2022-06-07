package com.example.stuffy.core.data.remote.network

import com.example.stuffy.core.data.remote.response.CategoryResponse
import com.example.stuffy.core.data.remote.response.ConfirmationResponse
import com.example.stuffy.core.data.remote.response.ProductResponse
import com.example.stuffy.core.data.remote.response.TransactionResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    @GET("products")
    suspend fun getProduct(): List<ProductResponse>
    @GET("categories")
    suspend fun getCategory(): List<CategoryResponse>
    @Multipart
    @POST("product")
    suspend fun createProduct(
        @Part files: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("name") name: RequestBody,
        @Part("location") location: RequestBody,
    ): ProductResponse
    @FormUrlEncoded
    @POST("transaction")
    suspend fun  createTransaction(
        @Field("productId") productId: String,
        @Field("email") email: String,
        @Field("status")status: String,
    ):TransactionResponse
    @GET("transactions")
    suspend fun getTransactions():List<TransactionResponse>
    @GET("transactions/{id}")
    suspend fun getTransactionsById(
        @Path("id") email: String
    ):List<TransactionResponse>
    @FormUrlEncoded
    @POST("confirmation")
    suspend fun createConfirmation(
        @Field("productId") productId: String,
        @Field("email") email: String,
        @Field("status")status: String,
        @Field("note")note: String,
    ):ConfirmationResponse
    @FormUrlEncoded
    @PUT("transaction/{id}")
    suspend fun updateTransactionStatus(
        @Path("id") id: String,
        @Field("status")status: String,
    ):TransactionResponse
    @FormUrlEncoded
    @PUT("confirmation/{id}")
    suspend fun updateConfirmationStatus(
        @Path("id") id: String,
        @Field("status")status: String,
    ):ConfirmationResponse

}