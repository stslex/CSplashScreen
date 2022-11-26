plugins {
    id("csplashscreen.android.library")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    libs.apply {
        implementation(ktor.client.core)
        implementation(ktor.client.android)
        implementation(ktor.client.jvm)
        implementation(ktor.client.serialization.json)
        implementation(ktor.client.negotivation)
        implementation(kotlinx.serialization.json)
        implementation(slf4j.android)
        implementation(koin.ktor)
        implementation(koin.logger.slf4j)
    }
}

android {
    namespace = "st.slex.core_network"
}