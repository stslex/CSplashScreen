plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.compose)
}

dependencies {
    implementation(project(":core:core"))

    implementation(libs.bundles.ktor)
    implementation(libs.bundles.okhttp)
    implementation(libs.coil.ktor)
}