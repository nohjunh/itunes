package com.nohjunh.android.watcha.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import com.nohjunh.android.watcha.assignment.core.designsystem.theme.AssignmentTheme
import com.nohjunh.android.watcha.assignment.ui.AssignmentApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentTheme(
                darkTheme = isSystemInDarkTheme(),
                androidTheme = false,
                disableDynamicTheming = true,
            ) {
                AssignmentApp()
            }
        }
    }
}
