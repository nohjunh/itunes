package com.nohjunh.android.watcha.assignment.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.nohjunh.android.watcha.assignment.core.designsystem.component.AssignmentTopAppBar
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import com.nohjunh.android.watcha.assignment.core.ui.AppendErrorBody
import com.nohjunh.android.watcha.assignment.core.ui.ErrorBody
import com.nohjunh.android.watcha.assignment.core.ui.LoadingIndicator
import com.nohjunh.android.watcha.assignment.core.ui.ShimmerTrackCard
import com.nohjunh.android.watcha.assignment.core.ui.SearchTrackCard
import com.nohjunh.android.watcha.feature.search.R

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel(),
    onShowSnackbar: (String) -> Unit,
) {
    val trackList = searchViewModel.trackList.collectAsLazyPagingItems()
    val searchSnackbarMessage by searchViewModel.snackbarMessage.collectAsStateWithLifecycle()

    Content(
        modifier = modifier,
        onSaveTrackClick = searchViewModel::saveTrackItem,
        trackList = trackList,
        onShowSnackbar = onShowSnackbar,
        searchSnackbarMessage = searchSnackbarMessage,
        onClearSnackbarMeesage = searchViewModel::clearSnackbarMessage
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier,
    onSaveTrackClick: (TrackItem) -> Unit = {},
    trackList: LazyPagingItems<TrackItem>,
    onShowSnackbar: (String) -> Unit = {},
    searchSnackbarMessage: String = "",
    onClearSnackbarMeesage: () -> Unit = {},
) {
    LaunchedEffect(searchSnackbarMessage) {
        if (searchSnackbarMessage.isNotEmpty()) {
            onShowSnackbar(searchSnackbarMessage)
            onClearSnackbarMeesage()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AssignmentTopAppBar(
                modifier = modifier,
                titleRes = R.string.search_headline
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (trackList.loadState.refresh) {
                is LoadState.Loading -> LoadingIndicator()
                is LoadState.Error ->
                    ErrorBody(
                        isSearch = true,
                        onClick = {
                            trackList.retry()
                        }
                    )

                else -> {
                    SearchBody(
                        trackList = trackList,
                        onSaveTrackClick = onSaveTrackClick
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBody(
    trackList: LazyPagingItems<TrackItem>,
    onSaveTrackClick: (TrackItem) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(count = trackList.itemCount) { index ->
            trackList[index]?.let { item ->
                SearchTrackCard(
                    modifier = Modifier.fillMaxWidth(),
                    trackItem = item,
                ) {
                    onSaveTrackClick(item)
                }
            }
        }
        item {
            AppendLoadingState(trackList)
        }
    }
}

@Composable
private fun AppendLoadingState(trackList: LazyPagingItems<TrackItem>) {
    when {
        isLoadStateAppendingLoading(trackList) -> {
            ShimmerTrackCard()
        }

        isLoadStateAppendingError(trackList) -> {
            AppendErrorBody(
                onClick = { trackList.retry() }
            )
        }

        else -> {
            LoadingIndicator()
        }
    }
}

@Composable
private fun isLoadStateAppendingError(trackList: LazyPagingItems<TrackItem>) =
    trackList.loadState.append is LoadState.Error

@Composable
private fun isLoadStateAppendingLoading(trackList: LazyPagingItems<TrackItem>) =
    trackList.loadState.append is LoadState.Loading
