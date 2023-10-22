package com.nohjunh.android.watcha.assignment.core.network.model.response


import com.google.gson.annotations.SerializedName

data class TrackResponse(
    @SerializedName("resultCount")
    val resultCount: Int?,
    @SerializedName("results")
    val trackItemResponse: List<TrackItemResponse>?,
)
