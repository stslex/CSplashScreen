plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))

    implementation(libs.androidx.paging.runtime)
}

android.namespace = "st.slex.csplashscreen.core.collection"