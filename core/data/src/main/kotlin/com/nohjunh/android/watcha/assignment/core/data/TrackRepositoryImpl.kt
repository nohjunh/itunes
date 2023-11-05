package com.nohjunh.android.watcha.assignment.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nohjunh.android.watcha.assignment.core.database.dao.StorageTrackItemDao
import com.nohjunh.android.watcha.assignment.core.database.dao.TrackItemDao
import com.nohjunh.android.watcha.assignment.core.database.model.StorageTrackItemEntity
import com.nohjunh.android.watcha.assignment.core.database.model.toDomainModel
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import com.nohjunh.android.watcha.assignment.core.network.datasource.SearchDataSource
import com.nohjunh.android.watcha.assignment.core.network.mediator.TrackMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(
    private val trackItemDao: TrackItemDao,
    private val storageTrackItemDao: StorageTrackItemDao,
    private val searchDataSource: SearchDataSource,
) : TrackRepository {
    companion object {
        private const val TERM = "greenday"
        private const val ENTITY = "song"
        private const val PAGING__PAGE_SIZE = 15
        private const val PAGING__PREFETCH_DISTANCE = 5
        private const val PAGING__INITIAL_LOAD_SIZE = 15
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getTrackList(): Flow<PagingData<TrackItem>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = PAGING__PAGE_SIZE,
                prefetchDistance = PAGING__PREFETCH_DISTANCE,
                initialLoadSize = PAGING__INITIAL_LOAD_SIZE
            ),
            remoteMediator = TrackMediator(
                term = TERM,
                entity = ENTITY,
                trackItemDao = trackItemDao,
                searchDataSource = searchDataSource
            ),
            pagingSourceFactory = {
                trackItemDao.pagingSource()
            }
        )

        return pager.flow.map { pagingData ->
            pagingData.map { it.toDomainModel() }
        }
    }

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
