package com.nohjunh.android.watcha.assignment.core.designsystem.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.nohjunh.android.watcha.assignment.core.designsystem.theme.Gray10
import com.nohjunh.android.watcha.assignment.core.designsystem.theme.Gray20
import com.nohjunh.android.watcha.assignment.core.designsystem.theme.White10
import com.nohjunh.android.watcha.assignment.core.designsystem.theme.White20

private const val SHIMMER_ANIMATION_DURATION = 1000

private val darkThemeColors = listOf(Gray20, Gray10, Gray20)
private val whiteThemeColors = listOf(White20, White10, White20)

fun Modifier.shimmerEffect(isDarkTheme: Boolean): Modifier = composed {
    val colors = if (isDarkTheme) darkThemeColors else whiteThemeColors

    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(SHIMMER_ANIMATION_DURATION)
        ), label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}

