plugins {
    id("csplashscreen.android.library")
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:photos"))

    implementation(libs.bundles.room)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.paging.runtime)
}

android.namespace = "com.stslex.csplashscreen.core.favourite"