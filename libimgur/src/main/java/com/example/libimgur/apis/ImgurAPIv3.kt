package com.example.libimgur.apis

import com.example.libimgur.models.GalleryResponse
import com.example.libimgur.models.TagsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ImgurAPIv3 {

    @GET("gallery/hot?album_preview=true")
    fun getGallery() : Call<GalleryResponse>

    @GET("tags")
    fun getTags() : Call<TagsResponse>
}