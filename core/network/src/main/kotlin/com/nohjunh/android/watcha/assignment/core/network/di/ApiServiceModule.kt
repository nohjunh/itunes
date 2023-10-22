package com.nohjunh.android.watcha.assignment.core.network.di

import com.nohjunh.android.watcha.assignment.core.network.service.SearchApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Singleton
    @Provides
    fun provideSearchApiService(
        okHttpclient: OkHttpClient,
        retrofit: Retrofit.Builder,
    ): SearchApiService =
        retrofit
            .client(okHttpclient)
            .build()
            .create(SearchApiService::class.java)

}
