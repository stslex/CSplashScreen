plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-ui"))
    implementation(project(":core-navigation"))
    implementation(project(":core-network"))
    implementation(libs.androidx.paging.runtime)

    implementation("io.coil-kt:coil-compose:2.2.2")
}

android {
    namespace = "st.slex.feature_topics"
}