package com.example.libimgur.apis

import com.example.libimgur.ImgurClient
import com.example.libimgur.params.Section
import org.junit.Assert.assertNotNull
import org.junit.Test

class ImgurAPIv3Tests {
    private val api = ImgurClient.api
    @Test
    fun `get tags working`(){
        val response = api.getTags().execute()
        assertNotNull(response.body())
    }

    @Test
    fun `get galleries - hot working`(){
        val response = api.getGallery(Section.HOT).execute()
        assertNotNull(response.body())
    }

    @Test
    fun `get galleries - top working`(){
        val response = api.getGallery(Section.TOP).execute()
        assertNotNull(response.body())
    }
}