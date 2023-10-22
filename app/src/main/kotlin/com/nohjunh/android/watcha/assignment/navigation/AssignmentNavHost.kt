package com.nohjunh.android.watcha.assignment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.nohjunh.android.watcha.assignment.feature.search.navigation.searchRoute
import com.nohjunh.android.watcha.assignment.feature.storage.navigation.storageRoute
import com.nohjunh.android.watcha.assignment.ui.AssignmentAppState

@Composable
fun AssignmentNavGraph(
    appState: AssignmentAppState,
    startDestination: String,
    modifier: Modifier = Modifier,
    onShowSnackbar: (String) -> Unit,
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        searchRoute(
            onShowSnackbar = onShowSnackbar
        )

        storageRoute(
            onShowSnackbar = onShowSnackbar
        )
    }
}
