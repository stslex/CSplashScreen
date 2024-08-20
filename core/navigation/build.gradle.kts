plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.compose)
    alias(libs.plugins.serialization)
}

dependencies {
    implementation(project(":core:core"))
    api(libs.androidx.compose.navigation)
    implementation(libs.kotlinx.serialization.json)
}

android.namespace = "st.slex.csplashscreen.core.navigation"