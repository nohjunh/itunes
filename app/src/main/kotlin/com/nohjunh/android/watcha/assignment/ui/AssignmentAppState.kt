package com.nohjunh.android.watcha.assignment.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nohjunh.android.watcha.assignment.core.common.navigation.AssignmentNavRoutes
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAssignmentAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): AssignmentAppState {
    return remember(
        navController,
        coroutineScope,
    ) {
        AssignmentAppState(
            navController,
            coroutineScope,
        )
    }
}

@Stable
class AssignmentAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: Set<String>
        get() = setOf(
            AssignmentNavRoutes.Search.route,
            AssignmentNavRoutes.Storage.route
        )
}
