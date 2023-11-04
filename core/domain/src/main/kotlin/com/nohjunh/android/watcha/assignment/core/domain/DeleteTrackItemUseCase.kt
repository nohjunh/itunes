package com.nohjunh.android.watcha.assignment.core.domain

import com.nohjunh.android.watcha.assignment.core.data.StorageRepository
import javax.inject.Inject

class DeleteTrackItemUseCase @Inject constructor(
    private val storageRepository: StorageRepository,
) {
    suspend operator fun invoke(trackId: Long): Result<Unit> =
        storageRepository.deleteTrackItem(trackId)
}
