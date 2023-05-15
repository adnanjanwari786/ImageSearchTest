package com.example.workdaytest.data.repository

import android.media.Image
import com.example.workdaytest.data.ImageSearchResponse
import com.example.workdaytest.network.ApiService
import com.example.workdaytest.network.ApiState
import com.example.workdaytest.network.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.Flow
import javax.inject.Inject

class ImageSearchRepositoryImpl @Inject constructor(private val apiService: ApiService)  : ImageSearchRepository {

    override suspend  fun getImages(query: String,
                  mediaType: String,
                  page: Int,
                  pageSize: Int): kotlinx.coroutines.flow.Flow<ImageSearchResponse> = flow {
        emit(apiService.searchImages(query,mediaType,page,pageSize))
    }.flowOn(Dispatchers.IO)


}