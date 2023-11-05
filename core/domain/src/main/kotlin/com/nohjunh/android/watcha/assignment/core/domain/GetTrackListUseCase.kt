package com.nohjunh.android.watcha.assignment.core.domain

import androidx.paging.PagingData
import com.nohjunh.android.watcha.assignment.core.data.TrackRepository
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrackListUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
) {
    operator fun invoke(): Flow<PagingData<TrackItem>> {
        return trackRepository.getTrackList()
    }

}
