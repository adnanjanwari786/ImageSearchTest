package com.example.workdaytest.network

import com.example.workdaytest.data.ImageSearchResponse

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:ImageSearchResponse) : ApiState()
    object Empty : ApiState()
}