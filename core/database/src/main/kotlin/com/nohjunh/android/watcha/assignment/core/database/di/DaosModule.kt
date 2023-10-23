package com.nohjunh.android.watcha.assignment.core.database.di

import com.nohjunh.android.watcha.assignment.core.database.AssignmentDatabase
import com.nohjunh.android.watcha.assignment.core.database.dao.StorageTrackItemDao
import com.nohjunh.android.watcha.assignment.core.database.dao.TrackItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesTrackItemDao(
        database: AssignmentDatabase,
    ): TrackItemDao = database.trackItemDao()

    @Provides
    fun providesStorageTrackItemDao(
        database: AssignmentDatabase,
    ): StorageTrackItemDao = database.storageTrackItemDao()

}
