package com.nohjunh.android.watcha.assignment.feature.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nohjunh.android.watcha.assignment.core.common.navigation.AssignmentNavRoutes
import com.nohjunh.android.watcha.assignment.feature.search.SearchScreen

fun NavGraphBuilder.searchRoute(
    onShowSnackbar: (String) -> Unit
) {
    composable(route = AssignmentNavRoutes.Search.route) {
        SearchScreen(
            onShowSnackbar = onShowSnackbar
        )
    }
}
