plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-ui"))
    implementation(project(":core-navigation"))
    implementation(project(":core-network"))

    libs.apply {
        implementation(hilt.android.core)
        kapt(hilt.android.compiler)
        implementation(androidx.paging.runtime)
    }
}

android {
    namespace = "st.slex.feature_search_photos"
}