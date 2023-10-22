package com.nohjunh.android.watcha.assignment.core.network.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.nohjunh.android.watcha.assignment.core.common.network.ApiResponse
import com.nohjunh.android.watcha.assignment.core.common.network.onException
import com.nohjunh.android.watcha.assignment.core.common.network.onSuccess
import com.nohjunh.android.watcha.assignment.core.database.dao.TrackItemDao
import com.nohjunh.android.watcha.assignment.core.database.model.TrackItemEntity
import com.nohjunh.android.watcha.assignment.core.network.datasource.SearchDataSource
import com.nohjunh.android.watcha.assignment.core.network.model.response.TrackListResponse
import com.nohjunh.android.watcha.assignment.core.network.model.response.toEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TrackMediator @Inject constructor(
    private val term: String,
    private val entity: String,
    private val trackItemDao: TrackItemDao,
    private val searchDataSource: SearchDataSource,
) : RemoteMediator<Int, TrackItemEntity>() {

    private var lastRequestedOffset = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TrackItemEntity>,
    ): MediatorResult {
        var result: MediatorResult? = null

        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0

                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    lastRequestedOffset += state.config.pageSize
                    lastRequestedOffset
                }
            }

            val trackListResult = searchDataSource.getTrackList(
                term = term,
                entity = entity,
                limit = state.config.pageSize,
                offset = loadKey
            )

            trackListResult.onSuccess { trackListResponse ->
                saveTrackList(trackListResponse, loadKey, loadType)

                result =
                    MediatorResult.Success(
                        endOfPaginationReached = isEndOfPagination(trackListResult)
                    )
            }.onException { e ->
                result = MediatorResult.Error(e)
            }

            return result
                ?: throw IllegalStateException("Unexpected scenario in TrackMediator.load")
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun saveTrackList(
        trackListResponse: TrackListResponse,
        loadKey: Int,
        loadType: LoadType,
    ) {
        trackListResponse.trackItemResponse?.let {
            val entities = it.mapIndexed { index, item ->
                item.toEntity(loadKey + index)
            }

            trackItemDao.saveTrackListAndDeleteIfRequired(
                trackList = entities,
                shouldDelete = loadType == LoadType.REFRESH
            )
        }
    }

    private fun isEndOfPagination(trackItemsResult: ApiResponse<TrackListResponse>) =
        if (trackItemsResult is ApiResponse.Success) {
            trackItemsResult.data.resultCount == 0
        } else {
            true
        }
}
