package com.example.stuffy.core.di

import androidx.room.Room
import com.example.stuffy.core.data.StuffyRepositoryImpl
import com.example.stuffy.core.data.local.LocalDataSource
import com.example.stuffy.core.data.local.room.StuffyDatabase
import com.example.stuffy.core.data.remote.RemoteDataSource
import com.example.stuffy.core.data.remote.network.ApiService
import com.example.stuffy.core.domain.repository.StuffyRepository
import com.example.stuffy.core.utils.AppExecutors
import com.example.stuffy.core.utils.BASE_URL
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<StuffyDatabase>().productDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            StuffyDatabase::class.java, "stuffy.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<StuffyRepository> {
       StuffyRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
}
