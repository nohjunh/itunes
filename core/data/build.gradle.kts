plugins {
    id("nohjunh.android.library")
    id("nohjunh.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.nohjunh.android.watcha.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.paging.ktx)
    implementation(libs.paging.compose)
}
