package com.nohjunh.android.watcha.assignment.core.network.service

import com.nohjunh.android.watcha.assignment.core.common.network.ApiResponse
import com.nohjunh.android.watcha.assignment.core.network.model.response.TrackListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    @GET("/search")
    suspend fun getTrackList(
        @Query("term") term: String,
        @Query("entity") entity: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): ApiResponse<TrackListResponse>

}
