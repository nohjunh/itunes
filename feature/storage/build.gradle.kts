plugins {
    id("nohjunh.android.feature")
    id("nohjunh.android.library")
    id("nohjunh.android.library.compose")
    id("nohjunh.android.hilt")
}

android {
    namespace = "com.nohjunh.android.watcha.feature.storage"
}

dependencies {
    implementation(libs.kotlinx.datetime)

}
