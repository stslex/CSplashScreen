plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core-network"))
    implementation(project(":core:photos"))

    implementation(libs.androidx.paging.runtime)
}

android.namespace = "com.stslex.csplashscreen.feature.search"