package com.nohjunh.android.watcha.assignment.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.nohjunh.android.watcha.assignment.core.model.TrackItem
import com.nohjunh.android.watcha.assignment.core.ui.LoadingIndicator
import com.nohjunh.android.watcha.assignment.core.ui.TrackCard

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
        searchViewModel = searchViewModel,
        trackList = trackList,
        onShowSnackbar = onShowSnackbar,
        searchSnackbarMessage = searchSnackbarMessage
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel,
    trackList: LazyPagingItems<TrackItem>,
    onShowSnackbar: (String) -> Unit,
    searchSnackbarMessage: String,
) {
    LaunchedEffect(searchSnackbarMessage) {
        if (searchSnackbarMessage.isNotEmpty()) {
            onShowSnackbar(searchSnackbarMessage)
            searchViewModel.clearSnackbarMessage()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        when (trackList.loadState.refresh) {
            is LoadState.Loading -> LoadingIndicator()
            is LoadState.Error -> LoadingIndicator()
            else -> {
                SearchBody(trackList)
            }
        }
    }
}

@Composable
fun SearchBody(trackList: LazyPagingItems<TrackItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(count = trackList.itemCount) { index ->
            trackList[index]?.let { item ->
                TrackCard(
                    modifier = Modifier.fillMaxWidth(),
                    trackItem = item
                )
            }
        }
        item {
            if (trackList.loadState.append is LoadState.Loading) {
                LoadingIndicator()
            }
        }
    }
}
