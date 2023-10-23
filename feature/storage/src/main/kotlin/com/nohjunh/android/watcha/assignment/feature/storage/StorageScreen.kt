package com.nohjunh.android.watcha.assignment.feature.storage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nohjunh.android.watcha.assignment.core.designsystem.component.AssignmentTopAppBar
import com.nohjunh.android.watcha.feature.storage.R

@Composable
fun StorageScreen(
    modifier: Modifier = Modifier,
    onShowSnackbar: (String) -> Unit,
) {
    Content(
        modifier = modifier,
        onShowSnackbar = onShowSnackbar,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier,
    onShowSnackbar: (String) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AssignmentTopAppBar(
                modifier = modifier,
                titleRes = R.string.storage_headline
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

        }
    }
}
