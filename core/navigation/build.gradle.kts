plugins {
    id("csplashscreen.android.library")
}

dependencies {
    implementation(project(":core:core"))
    api(libs.androidx.compose.navigation)
}

android.namespace = "com.stslex.csplashscreen.core.navigation"