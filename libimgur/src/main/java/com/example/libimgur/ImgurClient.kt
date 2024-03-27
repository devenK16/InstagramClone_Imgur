package com.example.libimgur

import com.example.libimgur.apis.ImgurAPIv3
import com.example.libimgur.converters.EnumConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ImgurClient {

    const val API_KEY = "b0ab637425bc77e"

    private val httpClient : OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("Authorization" , "Client-ID $API_KEY")
                    .build()
                it.proceed(request)
            }
            .build()
    }

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(EnumConverterFactory())
            .baseUrl("https://api.imgur.com/3/")
            .build()
    }

    val api : ImgurAPIv3 by lazy {
        retrofit.create(ImgurAPIv3::class.java)
    }
}