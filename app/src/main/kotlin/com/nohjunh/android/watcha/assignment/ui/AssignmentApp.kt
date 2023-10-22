package com.nohjunh.android.watcha.assignment.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nohjunh.android.watcha.assignment.core.common.navigation.AssignmentNavRoutes
import com.nohjunh.android.watcha.assignment.core.designsystem.component.AssignmentBackground
import com.nohjunh.android.watcha.assignment.core.designsystem.component.AssignmentNavigationBar
import com.nohjunh.android.watcha.assignment.core.designsystem.component.AssignmentNavigationBarItem
import com.nohjunh.android.watcha.assignment.core.ui.SnackbarBox
import com.nohjunh.android.watcha.assignment.navigation.AssignmentNavGraph
import com.nohjunh.android.watcha.assignment.navigation.TopLevelDestination
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
            snackbarHost = {
                SnackbarHost(
                    snackbarHostState,
                    snackbar = {
                        SnackbarBox(it) {
                            snackbarHostState.currentSnackbarData?.dismiss()
                        }
                    }
                )
            },
            bottomBar = {
                // 현재 목적지가 top-level일 경우에만 바텀 네비게이션 바 표시
                if (currentDestination in topLevelDestinations) {
                    AssignmentBottomBar(navController = appState.navController)
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(if (snackbarHostState.currentSnackbarData != null) 0.6f else 1f)
                    .padding(padding)
            ) {
                AssignmentNavGraph(
                    startDestination = AssignmentNavRoutes.Search.route,
                    appState = appState,
                    onShowSnackbar = { message ->
                        snackbarScope.launch {
                            snackbarHostState.showSnackbar(
                                message = message,
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                )
                if (currentDestination in topLevelDestinations) {
                    Divider( // 네비게이션바 border 상단 표현
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .align(Alignment.BottomCenter), // 스크린 바닥에 경계 표현
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun AssignmentBottomBar(
    navController: NavHostController,
) {
    AssignmentNavigationBar(
        modifier = Modifier,
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        TopLevelDestination.TopLevelItems.forEach { navItem ->
            AssignmentNavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true // 자기 자신이 또 스택 푸시가 되지 않도록 방지
                        restoreState = true
                    }
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (navItem.isBadgeUsage) {
                                Badge {
                                    Text(text = "empty")
                                }
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(navItem.unselectedIcon),
                            contentDescription = stringResource(navItem.title),
                        )
                    }
                },
                selectedIcon = {
                    BadgedBox(
                        badge = {
                            if (navItem.isBadgeUsage) {
                                Badge {
                                    Text(text = "empty")
                                }
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(navItem.selectedIcon),
                            contentDescription = stringResource(navItem.title)
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(navItem.title),
                    )
                }
            )
        }
    }
}
