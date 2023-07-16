pluginManagement {
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

include(":core:core")
include(":core:ui")
include(":core:navigation")
include(":core:network")
include(":core:photos")
include(":core:collection")

include(":feature:home")
include(":feature:collection")
include(":feature:user")
include(":feature:photo-detail")
include(":feature:search")
include(":feature:favourite")
include(":core:favourite")
