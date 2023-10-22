package com.nohjunh.android.watcha.assignment.core.network.datasource

import com.nohjunh.android.watcha.assignment.core.common.network.ApiResponse
import com.nohjunh.android.watcha.assignment.core.network.model.response.TrackListResponse

interface SearchDataSource {
    suspend fun getTrackList(
        term: String,
        entity: String,
        limit: Int,
        offset: Int,
    ): ApiResponse<TrackListResponse>

}
