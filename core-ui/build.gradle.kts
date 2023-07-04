plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-network"))
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.compose.constraintlayout)
}

android.namespace = "st.slex.core_ui"