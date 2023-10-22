package com.nohjunh.android.watcha.assignment.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nohjunh.android.watcha.assignment.core.model.TrackItem

@Entity(
    tableName = "track_resource"
)
data class TrackItemEntity(
    @PrimaryKey
    val trackId: Long,
    val artistName: String,
    val artworkUrl60: String,
    val collectionName: String,
    val trackName: String,
    val offsetId: Int,
)

fun TrackItemEntity.toDomainModel(): TrackItem {
    return TrackItem(
        id = trackId,
        artistName = artistName,
        artworkUrl60 = artworkUrl60,
        collectionName = collectionName,
        trackName = trackName
    )
}
