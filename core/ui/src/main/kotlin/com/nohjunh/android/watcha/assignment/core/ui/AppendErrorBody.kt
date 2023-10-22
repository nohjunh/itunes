package com.nohjunh.android.watcha.assignment.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nohjunh.android.watcha.assignment.core.designsystem.component.LottieImage
import com.nohjunh.android.watcha.core.ui.R

@Composable
fun AppendErrorBody(
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppendLoadErrorState()
        RetryOutlinedButton(
            onClick = onClick
        )
    }
}

@Composable
private fun AppendLoadErrorState() {
    Row(
        modifier = Modifier.height(80.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        LottieImage(
            modifier = Modifier.height(50.dp),
            rawAnimation = R.raw.append_error
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = stringResource(id = R.string.network_status_check),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun RetryOutlinedButton(
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = {
            onClick()
        },
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(
            text = stringResource(id = R.string.retry),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}
