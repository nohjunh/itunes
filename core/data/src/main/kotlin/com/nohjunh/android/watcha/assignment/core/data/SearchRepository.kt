package com.nohjunh.android.watcha.assignment.core.data

import androidx.paging.PagingData
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import kotlinx.coroutines.flow.Flow
import com.nohjunh.android.watcha.assignment.core.common.result.Result

interface SearchRepository {
    fun getTrackList(): Flow<PagingData<TrackItem>>
    suspend fun saveTrackItem(trackItem: TrackItem): Flow<Result<Unit>>

}
