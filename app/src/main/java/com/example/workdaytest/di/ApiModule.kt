package com.example.workdaytest.di

import com.example.workdaytest.network.ApiService
import com.example.workdaytest.network.retrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}
