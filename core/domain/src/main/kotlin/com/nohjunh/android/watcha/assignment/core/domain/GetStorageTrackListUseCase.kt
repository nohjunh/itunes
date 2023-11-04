package com.nohjunh.android.watcha.assignment.core.domain

import kotlinx.coroutines.flow.Flow
import com.nohjunh.android.watcha.assignment.core.data.StorageRepository
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import javax.inject.Inject

class GetStorageTrackListUseCase @Inject constructor(
    private val storageRepository: StorageRepository,
) {
    operator fun invoke(): Flow<List<TrackItem>> =
        storageRepository.getStorageTrackList()

}
