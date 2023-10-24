package com.nohjunh.android.watcha.assignment.core.network.model.response

import com.google.gson.annotations.SerializedName
import com.nohjunh.android.watcha.assignment.core.database.model.TrackItemEntity

data class TrackItemResponse(
    @SerializedName("trackId")
    val trackId: Long?,
    @SerializedName("artistName")
    val artistName: String?,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String?,
    @SerializedName("collectionName")
    val collectionName: String?,
    @SerializedName("trackName")
    val trackName: String?,
)

fun TrackItemResponse.toEntity(offsetId: Int): TrackItemEntity {
    return TrackItemEntity(
        trackId = trackId ?: 0,
        artistName = artistName ?: "default artistName",
        artworkUrl60 = artworkUrl60 ?: "default Url",
        collectionName = collectionName ?: "default collectionName",
        trackName = trackName ?: "default trackName",
        offsetId = offsetId
    )
}
