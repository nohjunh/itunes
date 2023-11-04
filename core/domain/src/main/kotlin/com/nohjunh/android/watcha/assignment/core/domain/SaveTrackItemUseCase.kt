package com.nohjunh.android.watcha.assignment.core.domain

import javax.inject.Inject
import com.nohjunh.android.watcha.assignment.core.data.StorageRepository
import com.nohjunh.android.watcha.assignment.core.model.TrackItem

class SaveTrackItemUseCase @Inject constructor(
    private val storageRepository: StorageRepository,
) {
    suspend operator fun invoke(trackItem: TrackItem): Result<Unit> =
        storageRepository.saveTrackItem(trackItem = trackItem)

}
