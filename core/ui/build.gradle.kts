plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core-network"))

    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.compose.constraintlayout)
}

android.namespace = "com.stslex.csplashscreen.core.ui"