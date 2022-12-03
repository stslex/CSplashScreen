plugins {
    id("csplashscreen.android.library")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))

    api(libs.androidx.navigation.compose)
}

android {
    namespace = "st.slex.core_navigation"
}