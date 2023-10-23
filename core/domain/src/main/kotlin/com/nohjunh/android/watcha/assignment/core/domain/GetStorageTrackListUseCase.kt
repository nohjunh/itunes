package com.nohjunh.android.watcha.assignment.core.domain

import com.nohjunh.android.watcha.assignment.core.data.SearchRepository
import kotlinx.coroutines.flow.Flow
import com.nohjunh.android.watcha.assignment.core.common.result.Result
import com.nohjunh.android.watcha.assignment.core.data.StorageRepository
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import javax.inject.Inject

class GetStorageTrackListUseCase @Inject constructor(
    private val storageRepository: StorageRepository,
) {
    suspend operator fun invoke(): Flow<Result<List<TrackItem>>> =
        storageRepository.getStorageTrackList()

}
