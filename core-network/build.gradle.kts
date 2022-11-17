plugins {
    id("csplashscreen.android.library")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    libs.apply {
        api(squareup.retrofit2.core)
        api(squareup.retrofit2.converter.gson)
        api(squareup.okhttp3.logging.interceptor)

        implementation(hilt.android.core)
        kapt(hilt.android.compiler)

        implementation(ktor.client.core)
        implementation(ktor.client.android)
        implementation(ktor.client.jvm)
        implementation(ktor.client.serialization.json)
        implementation(ktor.client.negotivation)
    }
    implementation("org.slf4j:slf4j-android:1.7.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

android {
    namespace = "st.slex.core_network"
}