plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
    kotlin("kapt")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:photos"))

    implementation(libs.bundles.room)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.paging.runtime)
}

android.namespace = "com.stslex.csplashscreen.feature.search"