plugins {
    id("csplashscreen.android.library")
    id("kotlin-parcelize")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-network"))

    libs.apply {
        implementation(libs.androidx.paging.runtime)
    }
}

android {
    namespace = "st.slex.core_photos"
}