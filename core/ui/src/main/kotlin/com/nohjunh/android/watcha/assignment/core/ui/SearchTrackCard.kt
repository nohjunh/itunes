package com.nohjunh.android.watcha.assignment.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nohjunh.android.watcha.assignment.core.designsystem.component.AssignmentIconButton
import com.nohjunh.android.watcha.assignment.core.designsystem.icon.AssignmentIcons
import com.nohjunh.android.watcha.assignment.core.model.TrackItem

@Composable
fun SearchTrackCard(
    modifier: Modifier = Modifier,
    trackItem: TrackItem,
    onClick: () -> Unit,
) {
    Card(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            ArtworkImage(
                imageUrl = trackItem.artworkUrl60,
                contentDescription = trackItem.trackName,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(15.dp))
            TrackContents(
                trackItem = trackItem,
                modifier = Modifier.weight(3f)
            )
            Spacer(modifier = Modifier.width(15.dp))
            AssignmentIconButton(
                onClick = { onClick() }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = AssignmentIcons.Storage),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
private fun TrackContents(
    trackItem: TrackItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = trackItem.trackName,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = trackItem.collectionName,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = trackItem.artistName,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
