plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-navigation"))
    implementation(project(":core-network"))

    implementation(libs.androidx.paging.runtime)
    implementation(libs.coil.compose)
}

android.namespace = "st.slex.feature_topics"