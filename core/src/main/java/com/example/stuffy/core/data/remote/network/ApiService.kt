package com.example.stuffy.core.data.remote.network

import com.example.stuffy.core.data.remote.response.ListProductResponse
import com.example.stuffy.core.data.remote.response.ProductResponse
import com.example.stuffy.core.utils.API_KEY
import retrofit2.http.GET

interface ApiService {
    @GET("api/products")
    suspend fun getProduct(): List<ProductResponse>

}