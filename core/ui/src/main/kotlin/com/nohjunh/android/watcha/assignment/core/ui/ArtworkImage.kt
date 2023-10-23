package com.nohjunh.android.watcha.assignment.core.ui

import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ArtworkImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        modifier = modifier.heightIn(min = 80.dp, max = 120.dp),
        model = imageUrl,
        contentDescription = contentDescription
    )
}
