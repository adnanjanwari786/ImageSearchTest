package com.example.workdaytest.viewmodel

import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workdaytest.data.ImageSearchResponse
import com.example.workdaytest.data.repository.ImageSearchRepository
import com.example.workdaytest.network.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class ImageSearchViewModel @Inject constructor(private val repository: ImageSearchRepository) :
    ViewModel() {

    private val _images = MutableStateFlow<ApiState>(ApiState.Loading)
    val images: StateFlow<ApiState> get() = _images


    fun searchImages(query: String, page: Int, pageSize: Int) {
        viewModelScope.launch {
            _images.value = ApiState.Loading
            val response = repository.getImages(query,"image", page, pageSize).catch { e->
                _images.value=ApiState.Failure(e)
            }.collect { data->
                _images.value=ApiState.Success(data)
            }

        }
    }

}