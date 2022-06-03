package com.example.stuffy.core.data.remote.network

import com.example.stuffy.core.data.remote.response.CategoryResponse
import com.example.stuffy.core.data.remote.response.ProductResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @GET("products")
    suspend fun getProduct(): List<ProductResponse>
    @GET("categories")
    suspend fun getCategory(): List<CategoryResponse>
    @POST("products")
    suspend fun createProduct(
        @Part files: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("name") name: RequestBody,
        @Part("location") location: RequestBody,
    ): ProductResponse
}