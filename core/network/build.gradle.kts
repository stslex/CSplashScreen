plugins {
    id("csplashscreen.android.library")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(project(":core:core"))

    implementation(libs.bundles.ktor)
    implementation(libs.bundles.okhttp)
}

android.namespace = "com.stslex.csplashscreen.core.network"
