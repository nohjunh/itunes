package com.nohjunh.android.watcha.assignment.core.domain

import com.nohjunh.android.watcha.assignment.core.common.result.Result
import com.nohjunh.android.watcha.assignment.core.data.StorageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteTrackItemUseCase @Inject constructor(
    private val storageRepository: StorageRepository,
) {
    suspend operator fun invoke(trackId: Long): Flow<Result<Unit>> =
        storageRepository.deleteTrackItem(trackId)
}
