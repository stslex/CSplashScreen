plugins {
    id("csplashscreen.android.library")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-network"))

    libs.apply {
        implementation(hilt.android.core)
        kapt(libs.hilt.android.compiler)
        implementation(libs.androidx.paging.runtime)
    }
}

android {
    namespace = "st.slex.core_collection"
}