package com.nohjunh.android.watcha.assignment.core.data

import com.nohjunh.android.watcha.assignment.core.database.dao.StorageTrackItemDao
import com.nohjunh.android.watcha.assignment.core.database.model.StorageTrackItemEntity
import com.nohjunh.android.watcha.assignment.core.database.model.toDomainModel
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storageTrackItemDao: StorageTrackItemDao,
) : StorageRepository {
    override fun getStorageTrackList(): Flow<List<TrackItem>> =
        storageTrackItemDao.getStorageTrackList().map { entityList ->
            entityList.map {
                it.toDomainModel()
            }
        }

    override suspend fun deleteTrackItem(trackId: Long): Result<Unit> =
        runCatching {
            storageTrackItemDao.deleteTrackItem(trackId)
        }

    override suspend fun saveTrackItem(trackItem: TrackItem): Result<Unit> =
        runCatching {
            storageTrackItemDao.saveTrackItem(
                StorageTrackItemEntity(
                    trackId = trackItem.id,
                    artistName = trackItem.artistName,
                    artworkUrl60 = trackItem.artworkUrl60,
                    collectionName = trackItem.collectionName,
                    trackName = trackItem.trackName,
                )
            )
        }
}
