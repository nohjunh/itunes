package com.nohjunh.android.watcha.assignment.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nohjunh.android.watcha.assignment.core.domain.GetTrackListUseCase
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    getTrackListUseCase: GetTrackListUseCase,
) : ViewModel() {
    private val _snackbarMessage = MutableStateFlow("")
    val snackbarMessage: StateFlow<String> = _snackbarMessage

    val trackList: Flow<PagingData<TrackItem>> =
        getTrackListUseCase().cachedIn(viewModelScope)

    fun setSnackbarMessage(message: String) {
        _snackbarMessage.value = message
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = ""
    }

}
