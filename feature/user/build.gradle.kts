plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:collection"))
    implementation(project(":core:navigation"))
    implementation(project(":core-network"))
    implementation(project(":core:photos"))

    implementation("androidx.compose.material:material:1.4.3")
}

android.namespace = "com.stslex.csplashscreen.feature.user"