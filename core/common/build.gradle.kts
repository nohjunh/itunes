import java.io.File
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("nohjunh.android.library")
    id("nohjunh.android.hilt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

val localProperties = Properties()
val localPropertiesFile = File(rootProject.rootDir, "local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(FileInputStream(localPropertiesFile))
}

android {
    namespace = "com.nohjunh.android.watcha.core.common"

    defaultConfig {
        buildConfigField(
            "String", "BASE_URL", "\"${localProperties["BASE_URL"] as String?}\""
        )
    }
}

secrets {
    defaultPropertiesFileName = "local.properties"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}
