package com.example.stuffy.core.domain.repository

import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.Filter
import com.example.stuffy.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface StuffyRepository {

    fun getListMovie(): Flow<Resource<List<Product>>>
    fun getCategory(): Flow<Resource<List<Filter>>>
    fun getFavoriteMovie(): Flow<List<Product>>

    fun setFavoriteMovie(movie: Product, state: Boolean)
}