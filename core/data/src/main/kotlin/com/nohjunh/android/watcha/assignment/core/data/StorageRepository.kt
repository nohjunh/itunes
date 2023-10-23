package com.nohjunh.android.watcha.assignment.core.data

import com.nohjunh.android.watcha.assignment.core.common.result.Result
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    suspend fun getStorageTrackList(): Flow<Result<List<TrackItem>>>
    suspend fun deleteTrackItem(trackId: Long): Flow<Result<Unit>>
    suspend fun saveTrackItem(trackItem: TrackItem): Flow<Result<Unit>>

}
