plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core-network"))
    implementation(project(":core:ui"))

    implementation(libs.androidx.paging.runtime)
}

android {
    namespace = "com.stslex.csplashscreen.core.photos"
}