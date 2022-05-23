package com.example.stuffy.core.data.remote

import android.util.Log
import com.example.stuffy.core.data.remote.network.ApiResponse
import com.example.stuffy.core.data.remote.network.ApiService
import com.example.stuffy.core.data.remote.response.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
class RemoteDataSource(private val apiService: ApiService) {


    suspend fun getListMovie(): Flow<ApiResponse<List<ProductResponse>>> {
        return flow {
            try {
                val response = apiService.getProduct()
                val dataArray = response
                if (dataArray.isNotEmpty()) {
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