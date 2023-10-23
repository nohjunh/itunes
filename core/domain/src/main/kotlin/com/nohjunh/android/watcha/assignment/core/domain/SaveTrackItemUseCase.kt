package com.nohjunh.android.watcha.assignment.core.domain

import com.nohjunh.android.watcha.assignment.core.data.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.nohjunh.android.watcha.assignment.core.common.result.Result
import com.nohjunh.android.watcha.assignment.core.model.TrackItem

class SaveTrackItemUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    suspend operator fun invoke(trackItem: TrackItem): Flow<Result<Unit>> =
        searchRepository.saveTrackItem(trackItem = trackItem)

}
