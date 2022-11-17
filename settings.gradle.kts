enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    @Suppress("UnstableApiUsage")
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CSplashScreen"
include(":app")
include(":core")
include(":core-test")
include(":core-ui")
include(":core-navigation")
include(":core-network")
include(":feature-home")
include(":feature-collection")
include(":core-photos")
include(":core-collection")
include(":feature-user")
include(":feature-photo-detail")
include(":feature-image-raw")
include(":feature-topics")
include(":feature-search-photos")
