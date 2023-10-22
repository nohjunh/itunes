package com.nohjunh.android.watcha.assignment.feature.storage.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nohjunh.android.watcha.assignment.core.common.navigation.AssignmentNavRoutes
import com.nohjunh.android.watcha.assignment.feature.storage.StorageScreen

fun NavGraphBuilder.storageRoute(
    onShowSnackbar: (String) -> Unit,
) {
    composable(route = AssignmentNavRoutes.Storage.route) {
        StorageScreen(
            onShowSnackbar = onShowSnackbar
        )
    }
}
