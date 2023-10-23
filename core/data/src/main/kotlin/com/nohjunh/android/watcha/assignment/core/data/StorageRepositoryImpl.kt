package com.nohjunh.android.watcha.assignment.core.data

import com.nohjunh.android.watcha.assignment.core.common.result.Result
import com.nohjunh.android.watcha.assignment.core.database.dao.StorageTrackItemDao
import com.nohjunh.android.watcha.assignment.core.database.model.StorageTrackItemEntity
import com.nohjunh.android.watcha.assignment.core.database.model.toDomainModel
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storageTrackItemDao: StorageTrackItemDao,
) : StorageRepository {
    override suspend fun getStorageTrackList(): Flow<Result<List<TrackItem>>> {
        return storageTrackItemDao.getStorageTrackList()
            .map { entityList ->
                try {
                    val trackList: List<TrackItem> = entityList.map { it.toDomainModel() }
                    Result.Success(trackList)
                } catch (e: Exception) {
                    Result.Failure(e.message ?: "DB Error", -1)
                }
            }
    }

    override suspend fun deleteTrackItem(trackId: Long): Flow<Result<Unit>> = flow {
        emit(Result.Loading)

        try {
            storageTrackItemDao.deleteTrackItem(trackId)
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Failure(e.message ?: "DB Error", -1))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun saveTrackItem(trackItem: TrackItem): Flow<Result<Unit>> = flow {
        emit(Result.Loading)

        try {
            storageTrackItemDao.insertTrackItem(
                StorageTrackItemEntity(
                    trackId = trackItem.id,
                    artistName = trackItem.artistName,
                    artworkUrl60 = trackItem.artworkUrl60,
                    collectionName = trackItem.collectionName,
                    trackName = trackItem.trackName,
                )
            )
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Failure(e.message ?: "DB Error", -1))
        }
    }.flowOn(Dispatchers.IO)

}
