plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.compose)
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:collection"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:photos"))

    implementation(libs.androidx.compose.material)
}
