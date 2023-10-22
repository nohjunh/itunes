package com.nohjunh.android.watcha.assignment.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nohjunh.android.watcha.assignment.core.designsystem.component.AssignmentBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AssignmentApp(
    appState: AssignmentAppState = rememberAssignmentAppState(),
    snackbarScope: CoroutineScope = rememberCoroutineScope(),
) {
    AssignmentBackground {
        val snackbarHostState = remember { SnackbarHostState() }
        val currentDestination = appState.currentDestination?.route
        val topLevelDestinations = appState.topLevelDestinations

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = {},
            bottomBar = {}
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(if (snackbarHostState.currentSnackbarData != null) 0.6f else 1f)
                    .padding(padding)
            ) {

            }
        }
    }
}
