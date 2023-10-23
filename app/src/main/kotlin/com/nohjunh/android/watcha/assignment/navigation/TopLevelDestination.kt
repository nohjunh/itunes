package com.nohjunh.android.watcha.assignment.navigation

import com.nohjunh.android.watcha.assignment.R
import com.nohjunh.android.watcha.assignment.core.common.navigation.AssignmentNavRoutes
import com.nohjunh.android.watcha.assignment.core.designsystem.icon.AssignmentIcons.Search
import com.nohjunh.android.watcha.assignment.core.designsystem.icon.AssignmentIcons.Storage

object TopLevelDestination {
    val TopLevelItems = listOf(
        TopLevelItem(
            title = R.string.search_title,
            selectedIcon = Search,
            unselectedIcon = Search,
            route = AssignmentNavRoutes.Search.route,
            isBadgeUsage = false
        ),
        TopLevelItem(
            title = R.string.storage_title,
            selectedIcon = Storage,
            unselectedIcon = Storage,
            route = AssignmentNavRoutes.Storage.route,
            isBadgeUsage = false
        )
    )
}

data class TopLevelItem(
    val title: Int,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val route: String,
    val isBadgeUsage: Boolean,
)
