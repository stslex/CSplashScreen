plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.compose)
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:network"))
    implementation(project(":core:ui"))

    implementation(libs.androidx.paging.runtime)
}