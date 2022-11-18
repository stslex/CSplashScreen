plugins {
    id("csplashscreen.android.library")
    id("csplashscreen.android.library.compose")
    id("kotlin-parcelize")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-ui"))
    implementation(project(":core-navigation"))
    implementation(project(":core-network"))
    implementation(project(":core-photos"))
    implementation(project(":core-collection"))
}

android {
    namespace = "st.slex.feature_home"
}