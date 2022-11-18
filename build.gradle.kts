import nl.littlerobots.vcu.plugin.versionCatalogUpdate

plugins {
    id("com.android.application") version "7.3.0" apply false
    id("com.android.library") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    kotlin("plugin.serialization") version "1.7.10"
    id("com.github.ben-manes.versions") version "0.44.0"
    id("nl.littlerobots.version-catalog-update") version "0.7.0"
}

buildscript {
    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.kotlin.serialization)
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}

versionCatalogUpdate {
    sortByKey.set(false)
    pin {
        versions.add("composeCompiler")
        libraries.add(libs.landscapist.glide)
        versions.add("kotlin")
    }
    keep {
        keepUnusedVersions.set(true)
        keepUnusedLibraries.set(true)
        keepUnusedPlugins.set(true)
    }
}