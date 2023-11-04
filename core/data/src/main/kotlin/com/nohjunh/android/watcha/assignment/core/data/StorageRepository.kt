package com.nohjunh.android.watcha.assignment.core.data

import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    fun getStorageTrackList(): Flow<List<TrackItem>>
    suspend fun deleteTrackItem(trackId: Long): Result<Unit>
    suspend fun saveTrackItem(trackItem: TrackItem): Result<Unit>

}
