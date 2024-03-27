package com.example.imguram.ui.data

import com.example.libimgur.ImgurClient
import com.example.libimgur.models.Image
import com.example.libimgur.models.Tag
import com.example.libimgur.params.Section

class ImgurRepository {
    val api = ImgurClient.api

    suspend fun getHotFeed(): List<Image>? {
        val response = api.getGallery(Section.HOT)
        return response.body()?.data
    }

    suspend fun getTopFeed(): List<Image>? {
        val response = api.getGallery(Section.TOP)
        return response.body()?.data
    }

    suspend fun getTags() : List<Tag>? {
        val response = api.getTags()
        return response.body()?.data?.tags
    }

    suspend fun getTagGallery(tagName: String): List<Image>? {
        val response = api.getTagGallery(tagName)
        return response.body()?.data?.items
    }

}