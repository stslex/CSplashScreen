plugins {
    alias(libs.plugins.convention.library.compose)
    alias(libs.plugins.convention.library)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))

    implementation(libs.androidx.paging.runtime)
}
