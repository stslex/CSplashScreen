import nl.littlerobots.vcu.plugin.versionCatalogUpdate

plugins {
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.application) apply false
    alias(libs.plugins.library) apply false
    alias(libs.plugins.serialization)
    alias(libs.plugins.versions)
    alias(libs.plugins.vcu)
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