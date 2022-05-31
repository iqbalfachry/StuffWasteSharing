package com.example.stuffy.core.data

import com.example.stuffy.core.data.local.LocalDataSource
import com.example.stuffy.core.data.remote.RemoteDataSource
import com.example.stuffy.core.data.remote.network.ApiResponse
import com.example.stuffy.core.data.remote.response.CategoryResponse
import com.example.stuffy.core.data.remote.response.ProductResponse
import com.example.stuffy.core.domain.model.Filter
import com.example.stuffy.core.domain.model.Product
import com.example.stuffy.core.domain.repository.StuffyRepository
import com.example.stuffy.core.utils.AppExecutors
import com.example.stuffy.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class StuffyRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : StuffyRepository {


    override fun getListMovie(): Flow<Resource<List<Product>>> =
        object : NetworkBoundResource<List<Product>, List<ProductResponse>>() {
            override fun loadFromDB(): Flow<List<Product>> {
                return localDataSource.getListMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Product>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ProductResponse>>> =
                remoteDataSource.getProduct()

            override suspend fun saveCallResult(data: List<ProductResponse>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()
    override fun getCategory(): Flow<Resource<List<Filter>>> =
        object : RemoteResource<List<Filter>, List<CategoryResponse>>() {



            override suspend fun createCall(): Flow<ApiResponse<List<CategoryResponse>>> =
                remoteDataSource.getCategory()


            override fun convertCallResult(data: List<CategoryResponse>): Flow<List<Filter>> {
                val result = data.map {
                    DataMapper.mapCategoryResponseToDomain(it)
                }
                return flow { emit(result) }
            }

            override fun emptyResult(): Flow<List<Filter>> {
                return flow { emit(emptyList<Filter>()) }
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Product>> {
        return localDataSource.getFavMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Product, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            moviesEntity?.let { localDataSource.setFavMovie(it, state) }
        }
    }


}