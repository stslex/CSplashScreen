plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.compose)
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:photos"))
    implementation(project(":core:favourite"))

    implementation(libs.androidx.paging.runtime)
}
