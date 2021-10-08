package com.example.marvelhub.application.modules

import com.example.marvelhub.BuildConfig
import com.example.marvelhub.data.remote.MarvelService
import com.example.marvelhub.data.remote.helper.calculatedMd5
import com.example.marvelhub.data.remote.mapper.CharacterEntityDtoMapper
import com.example.marvelhub.data.remote.source.RemoteDataSourceImpl
import com.example.marvelhub.data.repository.RemoteDataSource
import com.example.marvelhub.utils.Constants
import com.example.marvelhub.utils.Constants.REQUEST_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import timber.log.Timber
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(provideQueryInterseptor())
                .addInterceptor(loggingInterceptor)
                .build()

            return okHttpClient

        }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.API_BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MarvelService= retrofit.create(MarvelService::class.java)


    fun provideQueryInterseptor()= Interceptor { chain ->
        val originalRequest = chain.request()

        val timeStamp = System.currentTimeMillis()

        // Url customization: add query parameters
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("apikey", Constants.PUBLIC_API_KEY)
            .addQueryParameter("hash", calculatedMd5(timeStamp.toString() + Constants.PRIVATE_API_KEY + Constants.PUBLIC_API_KEY))
            .addQueryParameter("ts", "$timeStamp")
            .build()

        // Request customization: set custom url
        val request = originalRequest
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }
    @Singleton
    @Provides
    fun provideRemoteDataSource(marvelService: MarvelService,characterEntityDtoMapper: CharacterEntityDtoMapper):RemoteDataSource =RemoteDataSourceImpl(marvelService,characterEntityDtoMapper)

}