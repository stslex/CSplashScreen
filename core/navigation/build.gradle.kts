plugins {
    id("csplashscreen.android.library")
}

dependencies {
    implementation(project(":core:core"))
    api(libs.androidx.compose.navigation)
}

android.namespace = "st.slex.csplashscreen.core.navigation"