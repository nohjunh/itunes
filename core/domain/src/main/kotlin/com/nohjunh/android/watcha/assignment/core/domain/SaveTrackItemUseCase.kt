package com.nohjunh.android.watcha.assignment.core.domain

import com.nohjunh.android.watcha.assignment.core.data.TrackRepository
import javax.inject.Inject
import com.nohjunh.android.watcha.assignment.core.model.TrackItem

class SaveTrackItemUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
) {
    suspend operator fun invoke(trackItem: TrackItem): Result<Unit> =
        trackRepository.saveTrackItem(trackItem = trackItem)

}
