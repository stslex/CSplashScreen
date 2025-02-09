plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.compose) // TODO put in ui layer (need models refactoring)
    alias(libs.plugins.serialization)
}

dependencies {
    implementation(project(":core:core"))

    implementation(libs.bundles.ktor)
    implementation(libs.bundles.okhttp)
}
