plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:photos"))
    implementation(project(":core:favourite"))

    implementation(libs.androidx.paging.runtime)
}

android.namespace = "st.slex.csplashscreen.feature.favourite"