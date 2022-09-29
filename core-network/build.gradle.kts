plugins {
    id("csplashscreen.android.library")
    id("kotlin-parcelize")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    libs.squareup.apply {
        api(retrofit2.core)
        api(retrofit2.converter.gson)
        api(okhttp3.logging.interceptor)
    }
}

android {
    namespace = "st.slex.core_network"
}