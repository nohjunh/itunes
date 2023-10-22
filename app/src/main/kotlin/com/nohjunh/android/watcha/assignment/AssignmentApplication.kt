package com.nohjunh.android.watcha.assignment

import android.app.Application
import timber.log.Timber

class AssignmentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
