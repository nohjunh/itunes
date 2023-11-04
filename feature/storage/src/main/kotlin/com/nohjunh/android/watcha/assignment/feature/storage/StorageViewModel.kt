package com.nohjunh.android.watcha.assignment.feature.storage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjunh.android.watcha.assignment.core.common.result.asResult
import com.nohjunh.android.watcha.assignment.core.domain.DeleteTrackItemUseCase
import com.nohjunh.android.watcha.assignment.core.domain.GetStorageTrackListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.nohjunh.android.watcha.assignment.core.common.result.Result
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val getStorageTrackListUseCase: GetStorageTrackListUseCase,
    private val deleteTrackItemUseCase: DeleteTrackItemUseCase,
) : ViewModel() {

    private val _snackbarMessage = MutableStateFlow("")
    val snackbarMessage: StateFlow<String> = _snackbarMessage

    val storageUiState: StateFlow<StorageUiState> =
        getTrackList()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = StorageUiState.Loading,
            )

    fun getTrackList(): Flow<StorageUiState> {
        return getStorageTrackListUseCase()
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Success -> StorageUiState.Success(trackList = result.data)
                    is Result.Failure -> StorageUiState.LoadFailed
                    else -> StorageUiState.Loading
                }
            }
    }

    fun deleteTrackItem(trackId: Long) {
        viewModelScope.launch {
            deleteTrackItemUseCase(trackId)
                .onSuccess {
                    Timber.d("삭제 완료")
                }.onFailure { e ->
                    Timber.e(e.message)
                    _snackbarMessage.value = "문제가 발생했어요.\n다시 시도해주세요."
                }
        }
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = ""
    }

}
