// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.application) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.library) apply false
    alias(libs.plugins.serialization)
}
true // Needed to make the Suppress annotation work for the plugins block

buildscript {

    repositories {
        google()
        mavenCentral()
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}
