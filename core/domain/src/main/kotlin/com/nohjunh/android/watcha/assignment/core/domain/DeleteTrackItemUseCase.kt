package com.nohjunh.android.watcha.assignment.core.domain

import com.nohjunh.android.watcha.assignment.core.data.TrackRepository
import javax.inject.Inject

class DeleteTrackItemUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
) {
    suspend operator fun invoke(trackId: Long): Result<Unit> =
        trackRepository.deleteTrackItem(trackId)
}
