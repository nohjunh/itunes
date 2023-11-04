package com.nohjunh.android.watcha.assignment.core.domain

import com.nohjunh.android.watcha.assignment.core.data.TrackRepository
import kotlinx.coroutines.flow.Flow
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import javax.inject.Inject

class GetStorageTrackListUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
) {
    operator fun invoke(): Flow<List<TrackItem>> =
        trackRepository.getStorageTrackList()

}
