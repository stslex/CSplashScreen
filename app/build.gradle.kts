plugins {
    alias(libs.plugins.convention.application)
    alias(libs.plugins.convention.application.compose)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:database"))

    implementation(project(":core:collection"))
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:photos"))
    implementation(project(":core:favourite"))

    implementation(project(":feature:home"))
    implementation(project(":feature:collection"))
    implementation(project(":feature:user"))
    implementation(project(":feature:photo-detail"))
    implementation(project(":feature:search"))
    implementation(project(":feature:favourite"))
}