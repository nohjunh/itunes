package com.nohjunh.android.watcha.assignment.core.network.model.response


import com.google.gson.annotations.SerializedName

data class TrackItemResponse(
    @SerializedName("artistName")
    val artistName: String?,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String?,
    @SerializedName("collectionId")
    val collectionName: String?,
    @SerializedName("trackName")
    val trackName: String?,
)
