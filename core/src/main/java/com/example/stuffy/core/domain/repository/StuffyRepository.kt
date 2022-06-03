package com.example.stuffy.core.domain.repository

import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.Filter
import com.example.stuffy.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface StuffyRepository {

    fun getListMovie(): Flow<Resource<List<Product>>>
    fun createProduct(files: MultipartBody.Part, description: RequestBody, name: RequestBody, location: RequestBody): Flow<Resource<Product>>
    fun getCategory(): Flow<Resource<List<Filter>>>
    fun getFavoriteMovie(): Flow<List<Product>>

    fun setFavoriteMovie(movie: Product, state: Boolean)
}