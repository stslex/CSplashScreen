plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.room.library)
}

android.namespace = "st.slex.csplashscreen.core.database"

dependencies {
    implementation(project(":core:core"))
}