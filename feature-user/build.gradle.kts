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
    implementation(project(":core-photos"))
    implementation(project(":core-collection"))

    libs.apply {
        implementation(hilt.android.core)
        kapt(libs.hilt.android.compiler)
    }
}

android {
    namespace = "st.slex.feature_user"
}