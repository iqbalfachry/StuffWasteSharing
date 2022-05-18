package com.example.stuffy.core.data.local

import com.example.stuffy.core.data.local.entity.ProductEntity
import com.example.stuffy.core.data.local.room.ProductDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: ProductDao) {


    fun getListMovie(): Flow<List<ProductEntity>> = movieDao.getListMovie()

    fun getFavMovie(): Flow<List<ProductEntity>> = movieDao.getFavMovie()

    suspend fun insertMovie(movieList: List<ProductEntity>) = movieDao.insertMovie(movieList)

    fun setFavMovie(movie: ProductEntity, state: Boolean) {
        movie.isFav = state
        movieDao.setFavMovie(movie)
    }
}