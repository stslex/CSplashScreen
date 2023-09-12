plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose") // TODO put in ui layer (need models refactoring)
    kotlin("plugin.serialization")
}

dependencies {
    implementation(project(":core:core"))

    implementation(libs.bundles.ktor)
    implementation(libs.bundles.okhttp)
}

android.namespace = "st.slex.csplashscreen.core.network"
