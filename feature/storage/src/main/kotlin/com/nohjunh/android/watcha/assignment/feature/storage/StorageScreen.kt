package com.nohjunh.android.watcha.assignment.feature.storage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nohjunh.android.watcha.assignment.core.designsystem.component.AssignmentTopAppBar
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import com.nohjunh.android.watcha.assignment.core.ui.ErrorBody
import com.nohjunh.android.watcha.assignment.core.ui.LoadingIndicator
import com.nohjunh.android.watcha.assignment.core.ui.StorageTrackCard
import com.nohjunh.android.watcha.feature.storage.R

@Composable
fun StorageScreen(
    modifier: Modifier = Modifier,
    storageViewModel: StorageViewModel = hiltViewModel(),
    onShowSnackbar: (String) -> Unit,
) {
    val storageUiState by storageViewModel.storageUiState.collectAsStateWithLifecycle()
    val storageSnackbarMessage by storageViewModel.snackbarMessage.collectAsStateWithLifecycle()

    Content(
        modifier = modifier,
        storageUiState = storageUiState,
        storageViewModel = storageViewModel,
        onShowSnackbar = onShowSnackbar,
        storageSnackbarMessage = storageSnackbarMessage
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier,
    storageUiState: StorageUiState,
    storageViewModel: StorageViewModel,
    onShowSnackbar: (String) -> Unit,
    storageSnackbarMessage: String,
) {
    LaunchedEffect(storageSnackbarMessage) {
        if (storageSnackbarMessage.isNotEmpty()) {
            onShowSnackbar(storageSnackbarMessage)
            storageViewModel.clearSnackbarMessage()
        }
    }

    LaunchedEffect(Unit) {
        storageViewModel.getTrackList()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AssignmentTopAppBar(
                modifier = modifier,
                titleRes = R.string.storage_headline
            )
        }
    ) { paddingValues ->
        Box(
            modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (storageUiState) {
                is StorageUiState.Loading -> LoadingIndicator()
                is StorageUiState.Success -> StorageBody(
                    modifier = modifier,
                    storageViewModel = storageViewModel,
                    trackList = storageUiState.trackList
                )

                is StorageUiState.LoadFailed ->
                    ErrorBody(
                        isSearch = false,
                        onClick = {
                            storageViewModel.getTrackList()
                        }
                    )
            }
        }
    }
}

@Composable
fun StorageBody(
    modifier: Modifier = Modifier,
    storageViewModel: StorageViewModel,
    trackList: List<TrackItem>,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        contentPadding = PaddingValues(15.dp),
        content = {
            items(trackList.size) { index ->
                StorageTrackCard(
                    modifier = modifier,
                    trackItem = trackList[index],
                ) { trackId ->
                    storageViewModel.deleteTrackItem(trackId)
                }
            }
        }
    )
}
