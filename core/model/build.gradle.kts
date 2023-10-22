plugins {
    id("nohjunh.android.library")
}

android {
    namespace = "com.nohjunh.android.watcha.core.model"
}

dependencies {
    testImplementation(libs.junit)

    implementation(libs.kotlinx.datetime)
    implementation(libs.retrofit.gson)
}
