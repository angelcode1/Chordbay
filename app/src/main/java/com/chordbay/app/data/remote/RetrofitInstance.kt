package com.chordbay.app.data.remote

import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    // Thinned to only support the new Search API
    private const val BASE_URL = "https://www.googleapis.com/customsearch/v1/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            // ScalarsConverter removed as it's no longer needed for plain-text backend responses
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val api: ChordsBayApiService by lazy {
        retrofit.create(ChordsBayApiService.kt::class.java)
    }
}
