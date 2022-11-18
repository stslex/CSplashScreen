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
        implementation(hilt.android.core)
        kapt(hilt.android.compiler)
        implementation(ktor.client.core)
        implementation(ktor.client.android)
        implementation(ktor.client.jvm)
        implementation(ktor.client.serialization.json)
        implementation(ktor.client.negotivation)
        implementation(kotlinx.serialization.json)
        implementation(slf4j.android)
    }
}

android {
    namespace = "st.slex.core_network"
}