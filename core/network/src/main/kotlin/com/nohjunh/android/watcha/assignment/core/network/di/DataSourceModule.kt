package com.nohjunh.android.watcha.assignment.core.network.di

import com.nohjunh.android.watcha.assignment.core.network.datasource.SearchDataSource
import com.nohjunh.android.watcha.assignment.core.network.datasource.SearchDataSourceImpl
import com.nohjunh.android.watcha.assignment.core.network.service.SearchApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideSearchDataSource(searchApiService: SearchApiService): SearchDataSource =
        SearchDataSourceImpl(searchApiService)

}
