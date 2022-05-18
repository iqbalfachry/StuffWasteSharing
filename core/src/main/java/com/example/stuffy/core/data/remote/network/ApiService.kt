package com.example.stuffy.core.data.remote.network

import com.example.stuffy.core.data.remote.response.ListProductResponse
import com.example.stuffy.core.utils.API_KEY
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getMovies(): ListProductResponse

}