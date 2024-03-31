package com.example.imguram.ui.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imguram.ui.data.ImgurRepository
import com.example.libimgur.models.Image
import com.example.libimgur.models.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoryViewModel : ViewModel() {

    private val repo = ImgurRepository()
    private val _tags = MutableLiveData<List<Tag>>()

    val tags: LiveData<List<Tag>> = _tags

    fun fetchTags() = viewModelScope.launch(Dispatchers.IO) {
        _tags.postValue(repo.getTags())
    }
}