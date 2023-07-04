plugins {
    id("csplashscreen.android.library")
}

dependencies {
    implementation(project(":core"))

    api(libs.androidx.compose.navigation)
}

android.namespace = "st.slex.core_navigation"