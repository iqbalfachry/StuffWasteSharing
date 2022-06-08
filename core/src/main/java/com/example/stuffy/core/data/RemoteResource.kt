package com.example.stuffy.core.data

import com.example.stuffy.core.data.remote.network.ApiResponse
import kotlinx.coroutines.flow.*


abstract class RemoteResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                val data = convertCallResult(apiResponse.data)
                emitAll(data.map { Resource.Success(it) })
            }
            is ApiResponse.Empty -> {
                val data = emptyResult()
                emitAll(data.map { Resource.Success(it) })
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    protected abstract fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract fun convertCallResult(data: RequestType): Flow<ResultType>

    protected abstract fun emptyResult(): Flow<ResultType>

    fun asFlow(): Flow<Resource<ResultType>> = result
}