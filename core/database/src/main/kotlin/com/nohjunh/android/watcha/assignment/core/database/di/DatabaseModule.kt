package com.nohjunh.android.watcha.assignment.core.database.di

import android.content.Context
import androidx.room.Room
import com.nohjunh.android.watcha.assignment.core.database.AssignmentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAssignmentDatabase(@ApplicationContext context: Context): AssignmentDatabase =
        Room.databaseBuilder(
            context,
            AssignmentDatabase::class.java,
            "assignment-database"
        ).build()

}
