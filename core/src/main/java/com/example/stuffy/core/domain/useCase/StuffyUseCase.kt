package com.example.stuffy.core.domain.useCase


import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.Filter
import com.example.stuffy.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface StuffyUseCase {
    fun getListMovie(): Flow<Resource<List<Product>>>
    fun getCategory(): Flow<Resource<List<Filter>>>
    fun getFavMovie(): Flow<List<Product>>
    fun createProduct(files: MultipartBody.Part, description: RequestBody, name: RequestBody, location: RequestBody): Flow<Resource<Product>>

    fun setFavMovie(movie: Product, state: Boolean)
}