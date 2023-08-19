plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:photos"))

    implementation(libs.bundles.room)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.paging.runtime)
}

android.namespace = "st.slex.csplashscreen.feature.search"