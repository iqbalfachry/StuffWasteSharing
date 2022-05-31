package com.example.stuffy.core.domain.useCase


import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.Filter
import com.example.stuffy.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface StuffyUseCase {
    fun getListMovie(): Flow<Resource<List<Product>>>
    fun getCategory(): Flow<Resource<List<Filter>>>
    fun getFavMovie(): Flow<List<Product>>

    fun setFavMovie(movie: Product, state: Boolean)
}