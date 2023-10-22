package com.nohjunh.android.watcha.assignment.core.data

import androidx.paging.PagingData
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getTrackList(): Flow<PagingData<TrackItem>>
    suspend fun deleteTrackList()

}
