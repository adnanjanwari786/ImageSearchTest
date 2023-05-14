package com.example.workdaytest.data.repository

import com.example.workdaytest.data.ImageSearchResponse
import com.example.workdaytest.network.ApiState
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

interface ImageSearchRepository {

    suspend fun getImages(query:String,mediaType:String, page:Int, pageSize: Int): kotlinx.coroutines.flow.Flow<ImageSearchResponse>


}