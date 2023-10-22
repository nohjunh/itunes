package com.nohjunh.android.watcha.assignment.core.common.navigation

/**
 * 경로 문자열을 Composable() 메소드에 직접 입력하는 것 대신
 * sealed 클래스 활용 -> 유연한 경로 정의
 */
sealed class AssignmentNavRoutes(
    val route: String,
) {
    object Search : AssignmentNavRoutes("search")
    object Storage : AssignmentNavRoutes("storage")

}
