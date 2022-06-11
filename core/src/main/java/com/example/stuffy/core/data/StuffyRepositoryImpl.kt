package com.example.stuffy.core.data


import com.example.stuffy.core.data.local.LocalDataSource
import com.example.stuffy.core.data.local.entity.ProductEntity
import com.example.stuffy.core.data.local.entity.TransactionEntity
import com.example.stuffy.core.data.remote.RemoteDataSource
import com.example.stuffy.core.data.remote.network.ApiResponse
import com.example.stuffy.core.data.remote.response.CategoryResponse
import com.example.stuffy.core.data.remote.response.ConfirmationResponse
import com.example.stuffy.core.data.remote.response.ProductResponse
import com.example.stuffy.core.data.remote.response.TransactionResponse
import com.example.stuffy.core.domain.model.*
import com.example.stuffy.core.domain.repository.StuffyRepository
import com.example.stuffy.core.utils.AppExecutors
import com.example.stuffy.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StuffyRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : StuffyRepository {


    override fun getListMovie(): Flow<Resource<List<Product>>> =
        object : NetworkBoundResource<List<Product>, List<ProductResponse>>() {

            public override fun loadFromDB(): Flow<List<Product>> {
                return localDataSource.getListProduct().map{
                    DataMapper.mapListProductEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Product>?): Boolean {
                return true
            }
            override suspend  fun createCall(): Flow<ApiResponse<List<ProductResponse>>> =
                remoteDataSource.getProduct()


            override  suspend fun saveCallResult(data: List<ProductResponse>) {
                val products = ArrayList<ProductEntity>()
                data.map{
                    val product = DataMapper.mapProductResponseToEntity(it)
                    products.add(product)
                }


             localDataSource.insertProduct(products)
            }


        }.asFlow()
    override fun getTransactions(): Flow<Resource<List<ConfirmationTransaction>>> =
        object : NetworkBoundResource<List<ConfirmationTransaction>, List<TransactionResponse>>() {


            override suspend fun createCall(): Flow<ApiResponse<List<TransactionResponse>>> =
                remoteDataSource.getTransactions()


            override suspend fun saveCallResult(data: List<TransactionResponse>) {
                val transactions =ArrayList<TransactionEntity>()
                    data.map {
                        val transaction = DataMapper.mapTransactionResponseToEntity(it)
                        transactions.add(transaction)
                }
                localDataSource.insertTransaction(transactions)
            }

            override fun loadFromDB(): Flow<List<ConfirmationTransaction>> {
                return localDataSource.getListTransaction().map{
                    DataMapper.mapListTransactionEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<ConfirmationTransaction>?): Boolean {
                return true
            }


        }.asFlow()
    override fun createProduct(
        files: MultipartBody.Part,
        description: RequestBody,
        name: RequestBody,
        location: RequestBody
    ): Flow<Resource<Product>> =
        object : RemoteResource<Product, ProductResponse>() {

            override  fun createCall(): Flow<ApiResponse<ProductResponse>> =

                remoteDataSource.createProduct(files, description, name, location)


            override fun convertCallResult(data: ProductResponse): Flow<Product> {
                val result =
                    DataMapper.mapProductResponseToDomain(data)

                return flow { emit(result) }
            }

            override fun emptyResult(): Flow<Product> {

                return flow { emit(Product("", "", "", "", "", false)) }
            }


        }.asFlow()

    override fun createTransaction(
        productId: String,
        email: String,
        status: String
    ): Flow<Resource<ConfirmationTransaction>> =
        object : RemoteResource<ConfirmationTransaction, TransactionResponse>() {

            override  fun createCall(): Flow<ApiResponse<TransactionResponse>> =

                remoteDataSource.createTransaction(productId, email, status)


            override fun convertCallResult(data: TransactionResponse): Flow<ConfirmationTransaction> {
                val result =
                    DataMapper.mapTransactionResponseToDomain(data)

                return flow { emit(result) }
            }

            override fun emptyResult(): Flow<ConfirmationTransaction> {

                return flow { emit(ConfirmationTransaction("", "", "", "", "", null)) }
            }


        }.asFlow()
    override fun updateTransactionStatus(
        id: String,
        status: String
    ): Flow<Resource<ConfirmationTransaction>> =
        object : RemoteResource<ConfirmationTransaction, TransactionResponse>() {

            override fun createCall(): Flow<ApiResponse<TransactionResponse>> =

                remoteDataSource.updateTransactionStatus(id, status)


            override fun convertCallResult(data: TransactionResponse): Flow<ConfirmationTransaction> {
                val result =
                    DataMapper.mapTransactionResponseToDomain(data)

                return flow { emit(result) }
            }

            override fun emptyResult(): Flow<ConfirmationTransaction> {

                return flow { emit(ConfirmationTransaction("", "", "", "", "", null)) }
            }


        }.asFlow()

    override fun createConfirmation(
        productId: String,
        email: String,
        status: String, note: String
    ): Flow<Resource<ConfirmationTaker>> =
        object : RemoteResource<ConfirmationTaker, ConfirmationResponse>() {

            override  fun createCall(): Flow<ApiResponse<ConfirmationResponse>> =

                remoteDataSource.createConfirmation(productId, email, status, note)


            override fun convertCallResult(data: ConfirmationResponse): Flow<ConfirmationTaker> {
                val result =
                    DataMapper.mapConfirmationResponseToDomain(data)

                return flow { result?.let { emit(it) } }
            }

            override fun emptyResult(): Flow<ConfirmationTaker> {

                return flow { emit(ConfirmationTaker("", "", "", "", "", "","")) }
            }


        }.asFlow()

    override fun updateConfirmationStatus(
        id: String,

        status: String
    ): Flow<Resource<ConfirmationTaker>> =
        object : RemoteResource<ConfirmationTaker, ConfirmationResponse>() {

            override  fun createCall(): Flow<ApiResponse<ConfirmationResponse>> =

                remoteDataSource.updateConfirmationStatus(id, status)


            override fun convertCallResult(data: ConfirmationResponse): Flow<ConfirmationTaker> {
                val result =
                    DataMapper.mapConfirmationResponseToDomain(data)

                return flow { result?.let { emit(it) } }
            }

            override fun emptyResult(): Flow<ConfirmationTaker> {

                return flow { emit(ConfirmationTaker("", "", "", "", "", "","")) }
            }


        }.asFlow()


    override fun getCategory(): Flow<Resource<List<Filter>>> =
        object : RemoteResource<List<Filter>, List<CategoryResponse>>() {


            override  fun createCall(): Flow<ApiResponse<List<CategoryResponse>>> =
                remoteDataSource.getCategory()


            override fun convertCallResult(data: List<CategoryResponse>): Flow<List<Filter>> {
                val result = data.map {
                    DataMapper.mapCategoryResponseToDomain(it)
                }
                return flow { emit(result) }
            }

            override fun emptyResult(): Flow<List<Filter>> {
                return flow { emit(emptyList()) }
            }
        }.asFlow()



    override fun getTransactionsShare(): Flow<Resource<List<Share>>> =
        object : RemoteResource<List<Share>, List<TransactionResponse>>() {


            override  fun createCall(): Flow<ApiResponse<List<TransactionResponse>>> =
                remoteDataSource.getTransactions()


            override fun convertCallResult(data: List<TransactionResponse>): Flow<List<Share>> {
                val result = data.map {
                    DataMapper.mapTransactionResponseToDomainShare(it)
                }
                return flow { emit(result) }
            }

            override fun emptyResult(): Flow<List<Share>> {
                return flow { emit(emptyList()) }
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

    override fun getTransactionsTake(email: String): Flow<Resource<List<Take>>> =
        object : RemoteResource<List<Take>, List<TransactionResponse>>() {


            override fun createCall(): Flow<ApiResponse<List<TransactionResponse>>> =
                remoteDataSource.getTransactionsById(email)


            override fun convertCallResult(data: List<TransactionResponse>): Flow<List<Take>> {
                val result = data.map {
                    DataMapper.mapTransactionResponseToDomainTake(it)
                }
                return flow { emit(result) }
            }

            override fun emptyResult(): Flow<List<Take>> {
                return flow { emit(emptyList()) }
            }
        }.asFlow()


}