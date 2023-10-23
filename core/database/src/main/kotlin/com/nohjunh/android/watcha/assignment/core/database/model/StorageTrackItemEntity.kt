package com.nohjunh.android.watcha.assignment.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nohjunh.android.watcha.assignment.core.model.TrackItem

@Entity(
    tableName = "storage_track_resource"
)
data class StorageTrackItemEntity(
    @PrimaryKey
    val trackId: Long,
    val artistName: String,
    val artworkUrl60: String,
    val collectionName: String,
    val trackName: String,
)

fun StorageTrackItemEntity.toDomainModel(): TrackItem {
    return TrackItem(
        id = trackId,
        artistName = artistName,
        artworkUrl60 = artworkUrl60,
        collectionName = collectionName,
        trackName = trackName,
    )
}
