package com.nohjunh.android.watcha.assignment.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nohjunh.android.watcha.assignment.core.database.model.StorageTrackItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StorageTrackItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTrackItem(trackItem: StorageTrackItemEntity)

    @Query("SELECT * FROM storage_track_resource")
    fun getStorageTrackList(): Flow<List<StorageTrackItemEntity>>

    @Query("DELETE FROM storage_track_resource WHERE trackId = :trackId")
    suspend fun deleteTrackItem(trackId: Long)

}
