package com.nohjunh.android.watcha.assignment.feature.storage

import com.nohjunh.android.watcha.assignment.core.model.TrackItem

sealed class StorageUiState {
    object Loading : StorageUiState()

    data class Success(
        val trackList: List<TrackItem>,
    ) : StorageUiState()

    object LoadFailed : StorageUiState()
}
