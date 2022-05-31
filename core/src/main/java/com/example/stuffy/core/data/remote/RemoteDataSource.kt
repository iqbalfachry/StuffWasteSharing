package com.example.stuffy.core.data.remote

import android.util.Log
import com.example.stuffy.core.data.remote.network.ApiResponse
import com.example.stuffy.core.data.remote.network.ApiService
import com.example.stuffy.core.data.remote.response.CategoryResponse
import com.example.stuffy.core.data.remote.response.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
class RemoteDataSource(private val apiService: ApiService) {


    suspend fun getProduct(): Flow<ApiResponse<List<ProductResponse>>> {
        return flow {
            try {
                val response = apiService.getProduct()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getCategory(): Flow<ApiResponse<List<CategoryResponse>>> {
        return flow {
            try {
                val response = apiService.getCategory()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {

        const val TAG = "Remote Data Source"
    }
}