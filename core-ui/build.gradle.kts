plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-network"))

    libs.apply {
        api(android.material)
        api(androidx.appcompat)
        api(bundles.compose)
        api(bundles.accompanist)
        api(landscapist.glide)
        api(koin.compose)
        androidTestApi(androidx.compose.ui.test.junit4)
    }
}

android {
    namespace = "st.slex.core_ui"
}