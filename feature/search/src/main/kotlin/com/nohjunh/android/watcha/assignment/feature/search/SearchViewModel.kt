package com.nohjunh.android.watcha.assignment.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nohjunh.android.watcha.assignment.core.common.result.onFailure
import com.nohjunh.android.watcha.assignment.core.common.result.onSuccess
import com.nohjunh.android.watcha.assignment.core.domain.GetTrackListUseCase
import com.nohjunh.android.watcha.assignment.core.domain.SaveTrackItemUseCase
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getTrackListUseCase: GetTrackListUseCase,
    private val saveTrackItemUseCase: SaveTrackItemUseCase,
) : ViewModel() {
    val trackList: Flow<PagingData<TrackItem>> =
        getTrackListUseCase().cachedIn(viewModelScope)

    private val _snackbarMessage = MutableStateFlow("")
    val snackbarMessage: StateFlow<String> = _snackbarMessage

    fun saveTrackItem(trackItem: TrackItem) = viewModelScope.launch {
        saveTrackItemUseCase(trackItem).collect { result ->
            result.onSuccess {
                _snackbarMessage.value = "보관함에 저장했어요."
            }.onFailure { errorMessage, code ->
                Timber.e(errorMessage, code)
                _snackbarMessage.value = "문제가 발생했어요.\n다시 시도해주세요."
            }
        }
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = ""
    }

}
