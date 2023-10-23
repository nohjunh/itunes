package com.nohjunh.android.watcha.assignment.core.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.nohjunh.android.watcha.assignment.core.common.result.Result
import com.nohjunh.android.watcha.assignment.core.data.StorageRepository
import com.nohjunh.android.watcha.assignment.core.model.TrackItem

class SaveTrackItemUseCase @Inject constructor(
    private val storageRepository: StorageRepository,
) {
    suspend operator fun invoke(trackItem: TrackItem): Flow<Result<Unit>> =
        storageRepository.saveTrackItem(trackItem = trackItem)

}
