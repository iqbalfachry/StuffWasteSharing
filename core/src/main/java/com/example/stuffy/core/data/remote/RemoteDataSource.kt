package com.example.stuffy.core.data.remote

import android.util.Log
import com.example.stuffy.core.data.remote.network.ApiResponse
import com.example.stuffy.core.data.remote.network.ApiService
import com.example.stuffy.core.data.remote.response.CategoryResponse
import com.example.stuffy.core.data.remote.response.ConfirmationResponse
import com.example.stuffy.core.data.remote.response.ProductResponse
import com.example.stuffy.core.data.remote.response.TransactionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.Multipart

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
    suspend fun getTransactions(): Flow<ApiResponse<List<TransactionResponse>>> {
        return flow {
            try {
                val response = apiService.getTransactions()
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
    suspend fun getTransactionsById(email:String): Flow<ApiResponse<List<TransactionResponse>>> {
        return flow {
            try {
                val response = apiService.getTransactionsById(email)
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
    suspend fun createProduct(files:MultipartBody.Part,description:RequestBody,name:RequestBody,location:RequestBody): Flow<ApiResponse<ProductResponse>> {
        return flow {
            try {
                val response = apiService.createProduct(files,description,name,location)
                if (response.description.isNotEmpty()&&response.name.isNotEmpty()&&response.location.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                }else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    suspend fun createTransaction( productId: String,
                                   email: String,
                                  status: String,): Flow<ApiResponse<TransactionResponse>> {
        return flow {
            try {
                val response = apiService.createTransaction(productId,email, status)
                if (response.product.id.isNotEmpty()&&response.sharer.id.isNotEmpty()&&response.status.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                }else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    suspend fun updateTransactionStatus( id: String,

                                   status: String,): Flow<ApiResponse<TransactionResponse>> {
        return flow {
            try {
                val response = apiService.updateTransactionStatus(id, status)
                if (response.product.id.isNotEmpty()&&response.sharer.id.isNotEmpty()&&response.status.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                }else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    suspend fun updateConfirmationStatus( id: String,

                                   status: String,): Flow<ApiResponse<ConfirmationResponse>> {
        return flow {
            try {
                val response = apiService.updateConfirmationStatus(id,status)
                if (response.id.isNotEmpty()&&response.note.isNotEmpty()&&response.status.isNotEmpty()) {
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
    suspend fun createConfirmation(        productId: String,
                                            email: String,
                                         status: String,
                                            note: String,): Flow<ApiResponse<ConfirmationResponse>> {
        return flow {
            try {
                val response = apiService.createConfirmation(productId,email, status,note)
                if (response.id.isNotEmpty()&&response.note.isNotEmpty()&&response.status.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                }else {
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