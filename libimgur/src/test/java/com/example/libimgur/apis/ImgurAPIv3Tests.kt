package com.example.libimgur.apis

import com.example.libimgur.ImgurAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.TestCase.assertNotNull
import okhttp3.OkHttpClient
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ImgurAPIv3Tests {

    private val client = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("Authorization" , "Client-ID b0ab637425bc77e")
                .build()
            it.proceed(request)
        }
        .build()

    private val moshi =
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://api.imgur.com/3/")
        .build()

    private val api = retrofit.create(ImgurAPIv3::class.java)

    @Test
    fun `get tags working`(){
        val response = api.getTags().execute()
        assertNotNull(response.body())
    }

    @Test
    fun `get galleries working`(){
        val response = api.getGallery().execute()
        assertNotNull(response.body())
    }
}