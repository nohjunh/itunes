package com.nohjunh.android.watcha.assignment.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nohjunh.android.watcha.assignment.core.database.dao.TrackItemDao
import com.nohjunh.android.watcha.assignment.core.database.model.toDomainModel
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import com.nohjunh.android.watcha.assignment.core.network.datasource.SearchDataSource
import com.nohjunh.android.watcha.assignment.core.network.mediator.TrackMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val trackItemDao: TrackItemDao,
    private val searchDataSource: SearchDataSource,
) : SearchRepository {
    companion object {
        private const val TERM = "greenday"
        private const val ENTITY = "song"
        private const val PAGE_SIZE = 20
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getTrackList(): Flow<PagingData<TrackItem>> {
        val pager = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
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

    override suspend fun deleteTrackList() {
        trackItemDao.deleteTrackList()
    }

}
