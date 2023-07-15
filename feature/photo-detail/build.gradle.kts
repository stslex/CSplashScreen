plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))

    implementation(libs.androidx.compose.constraintlayout)
}

android.namespace = "com.stslex.csplashscreen.feature.feature_photo_detail"