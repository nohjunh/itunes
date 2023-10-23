package com.nohjunh.android.watcha.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nohjunh.android.watcha.assignment.core.designsystem.theme.AssignmentTheme
import com.nohjunh.android.watcha.assignment.ui.AssignmentApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

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
