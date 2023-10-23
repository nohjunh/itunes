package com.nohjunh.android.watcha.assignment.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nohjunh.android.watcha.assignment.core.designsystem.icon.AssignmentIcons
import com.nohjunh.android.watcha.assignment.core.model.TrackItem

@Composable
fun StorageTrackCard(
    modifier: Modifier,
    trackItem: TrackItem,
    onClick: (Long) -> Unit,
) {
    Card(
        modifier = modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        onClick(trackItem.id)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        painter = painterResource(id = AssignmentIcons.CancelFilled),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArtworkImage(
                    modifier = Modifier.size(80.dp),
                    imageUrl = trackItem.artworkUrl60,
                    contentDescription = trackItem.trackName
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            TrackContents(
                item = trackItem
            )
        }
    }
}

@Composable
private fun TrackContents(
    modifier: Modifier = Modifier,
    item: TrackItem,
) {
    Column(
        modifier = modifier.padding(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = item.trackName,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.collectionName,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.artistName,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
