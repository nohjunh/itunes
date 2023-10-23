package com.nohjunh.android.watcha.assignment.feature.storage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjunh.android.watcha.assignment.core.common.result.onFailure
import com.nohjunh.android.watcha.assignment.core.common.result.onSuccess
import com.nohjunh.android.watcha.assignment.core.domain.DeleteTrackItemUseCase
import com.nohjunh.android.watcha.assignment.core.domain.GetStorageTrackListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val getStorageTrackListUseCase: GetStorageTrackListUseCase,
    private val deleteTrackItemUseCase: DeleteTrackItemUseCase,
) : ViewModel() {

    private val _storageUiState =
        MutableStateFlow<StorageUiState>(StorageUiState.Loading)
    val storageUiState = _storageUiState.asStateFlow()

    private val _snackbarMessage = MutableStateFlow("")
    val snackbarMessage: StateFlow<String> = _snackbarMessage

    fun getTrackList() = viewModelScope.launch {
        _storageUiState.value = StorageUiState.Loading

        getStorageTrackListUseCase().collect { result ->
            result.onSuccess {
                _storageUiState.value =
                    StorageUiState.Success(
                        trackList = it
                    )
            }.onFailure { errorMessage, code ->
                Timber.e(errorMessage, code)
                _snackbarMessage.value = "보관함을 불러오지 못했어요."
                _storageUiState.value =
                    StorageUiState.LoadFailed
            }
        }
    }

    fun deleteTrackItem(trackId: Long) = viewModelScope.launch {
        deleteTrackItemUseCase(trackId).collect { result ->
            result.onSuccess {
                Timber.d("삭제 완료")
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
