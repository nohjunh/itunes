package com.nohjunh.android.watcha.assignment.core.network.datasource

import com.nohjunh.android.watcha.assignment.core.common.network.ApiResponse
import com.nohjunh.android.watcha.assignment.core.network.model.response.TrackListResponse
import com.nohjunh.android.watcha.assignment.core.network.service.SearchApiService
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchApiService: SearchApiService,
) : SearchDataSource {
    override suspend fun getTrackList(
        term: String,
        entity: String,
        limit: Int,
        offset: Int,
    ): ApiResponse<TrackListResponse> =
        searchApiService.getTrackList(
            term = term,
            entity = entity,
            limit = limit,
            offset = offset
        )

}
