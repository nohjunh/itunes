package com.nohjunh.android.watcha.assignment.core.domain

import androidx.paging.PagingData
import com.nohjunh.android.watcha.assignment.core.data.SearchRepository
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrackListUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    operator fun invoke(): Flow<PagingData<TrackItem>> {
        return searchRepository.getTrackList()
    }

}
