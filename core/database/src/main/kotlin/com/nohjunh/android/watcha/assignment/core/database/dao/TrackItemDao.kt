package com.nohjunh.android.watcha.assignment.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.nohjunh.android.watcha.assignment.core.database.model.TrackItemEntity

@Dao
interface TrackItemDao {
    @Upsert
    suspend fun upsertTrackList(trackList: List<TrackItemEntity>)

    @Query("SELECT * FROM track_resource ORDER BY offsetId ASC")
    fun pagingSource(): PagingSource<Int, TrackItemEntity>

    @Query("DELETE FROM track_resource")
    suspend fun deleteTrackList()

    @Transaction
    suspend fun saveTrackListAndDeleteIfRequired(
        trackList: List<TrackItemEntity>,
        shouldDelete: Boolean,
    ) {
        if (shouldDelete) {
            deleteTrackList()
        }
        upsertTrackList(trackList)
    }
}
