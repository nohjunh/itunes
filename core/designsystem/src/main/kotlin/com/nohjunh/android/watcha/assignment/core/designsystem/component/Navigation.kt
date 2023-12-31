package com.nohjunh.android.watcha.assignment.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * @param selected 해당 아이템이 선택되었는지 여부를 나타내는 Boolean 값
 * @param onClick 아이템이 선택되었을 때 호출되는 콜백 함수
 * @param icon  아이템에 표시할 아이콘 컨텐츠
 * @param modifier 아이템에 적용할 Modifier
 * @param selectedIcon 아이템이 선택되었을 때 표시할 아이콘 컨텐츠로, 기본값은 icon과 동일
 * @param enabled 아이템의 활성화 여부를 나타내는 Boolean 값으로, false일 경우 클릭이 불가능하고 비활성화 상태로 표시
 * @param label 아이템의 텍스트 라벨 컨텐츠 -> 이 값이 제공되면 아이템에 텍스트 라벨이 표시
 * @param alwaysShowLabel false로 설정하면 아이템이 선택되지 않았을 때에만 라벨이 표시되고, 선택되었을 때는 라벨이 숨겨짐. 기본값은 true로 항상 라벨을 표시
 */
@Composable
fun RowScope.AssignmentNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = AssignmentNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = AssignmentNavigationDefaults.navigationContentColor(),
            selectedTextColor = AssignmentNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = AssignmentNavigationDefaults.navigationContentColor(),
            indicatorColor = AssignmentNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

/**
 * @param modifier 네비게이션 바에 적용할 Modifier
 * @param content 네비게이션 바 안에 들어갈 컨텐츠
 */
@Composable
fun AssignmentNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = AssignmentNavigationDefaults.navigationContentColor(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 1.dp,
        content = content,
    )
}

object AssignmentNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.tertiary

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimary

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.background
}
