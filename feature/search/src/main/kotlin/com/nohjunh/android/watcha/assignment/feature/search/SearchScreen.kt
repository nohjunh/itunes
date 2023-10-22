package com.nohjunh.android.watcha.assignment.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onShowSnackbar: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Search"
        )
    }
}