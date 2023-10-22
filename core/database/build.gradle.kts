plugins {
    id("nohjunh.android.library")
    id("nohjunh.android.hilt")
    id("nohjunh.android.room")
}

android {
    namespace = "com.nohjunh.android.watcha.core.database"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.paging.ktx)
    implementation(libs.paging.compose)
}
