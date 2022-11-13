package com.peacecodetech.countries.network

import com.peacecodetech.countries.utils.API_CONNECT_TIMEOUT
import com.peacecodetech.countries.utils.API_READ_TIMEOUT
import com.peacecodetech.countries.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


interface CountryApiService {

    @GET("character")
    suspend fun getAllCountries(): CountryApiResponse
}

fun createApiService(): CountryApiService {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(createHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(CountryApiService::class.java)
}

private fun createHttpClient(): OkHttpClient {
    val interceptor = createLoggingInterceptor()
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(API_READ_TIMEOUT, TimeUnit.SECONDS)
        .build()
}

private fun createLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return interceptor
}
