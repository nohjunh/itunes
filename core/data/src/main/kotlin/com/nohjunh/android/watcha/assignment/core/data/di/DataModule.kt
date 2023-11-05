package com.nohjunh.android.watcha.assignment.core.data.di

import com.nohjunh.android.watcha.assignment.core.data.TrackRepository
import com.nohjunh.android.watcha.assignment.core.data.TrackRepositoryImpl
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
        searchRepository: TrackRepositoryImpl,
    ): TrackRepository

}
