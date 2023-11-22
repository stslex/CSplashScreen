plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    api(libs.androidx.compose.navigation)

    implementation(libs.androidx.paging.runtime)
}

android.namespace = "st.slex.csplashscreen.core.ui"