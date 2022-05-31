package com.example.stuffy.core.data.remote.network

import com.example.stuffy.core.data.remote.response.CategoryResponse
import com.example.stuffy.core.data.remote.response.ProductResponse
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProduct(): List<ProductResponse>
    @GET("categories")
    suspend fun getCategory(): List<CategoryResponse>

}