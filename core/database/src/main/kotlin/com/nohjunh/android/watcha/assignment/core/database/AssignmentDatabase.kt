package com.nohjunh.android.watcha.assignment.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nohjunh.android.watcha.assignment.core.database.dao.StorageTrackItemDao
import com.nohjunh.android.watcha.assignment.core.database.dao.TrackItemDao
import com.nohjunh.android.watcha.assignment.core.database.model.StorageTrackItemEntity
import com.nohjunh.android.watcha.assignment.core.database.model.TrackItemEntity

@Database(
    entities = [TrackItemEntity::class, StorageTrackItemEntity::class],
    version = 1
)
abstract class AssignmentDatabase : RoomDatabase() {
    abstract fun trackItemDao(): TrackItemDao
    abstract fun storageTrackItemDao(): StorageTrackItemDao

}
