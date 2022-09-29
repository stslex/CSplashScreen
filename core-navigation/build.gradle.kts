plugins {
    id("csplashscreen.android.library")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    libs.apply {
        api(androidx.navigation.compose)
        api(hilt.navigation.compose)
    }
}

android {
    namespace = "st.slex.core_navigation"
}