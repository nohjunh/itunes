package com.nohjunh.android.watcha.assignment.core.data.di

import com.nohjunh.android.watcha.assignment.core.data.SearchRepository
import com.nohjunh.android.watcha.assignment.core.data.SearchRepositoryImpl
import com.nohjunh.android.watcha.assignment.core.data.StorageRepository
import com.nohjunh.android.watcha.assignment.core.data.StorageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Singleton
    @Binds
    fun bindSearchRepository(
        searchRepository: SearchRepositoryImpl,
    ): SearchRepository

    @Singleton
    @Binds
    fun bindStorageRepository(
        storageRepository: StorageRepositoryImpl,
    ): StorageRepository

}
