package st.slex.csplashscreen

import AppExt.currentLibs
import AppExt.findVersionString
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    val libs = currentLibs

    commonExtension.apply {

        buildFeatures.compose = true
        composeOptions.kotlinCompilerExtensionVersion = libs.findVersionString("composeCompiler")

        dependencies {
            val composeBom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(composeBom))
            add("androidTestImplementation", platform(composeBom))

            val composeApi = libs.findBundle("compose").get()
            add("implementation", composeApi)

//            val composeManifest = libs.findBundle("androidx-compose-manifest").get()
//            add("debugImplementation", composeManifest)

            val tooling = libs.findLibrary("androidx-compose-tooling").get()
            add("debugImplementation", tooling)

            val composeTest = libs.findLibrary("androidx-compose-ui-test-junit4").get()
            add("androidTestImplementation", composeTest)

            val accompanist = libs.findBundle("accompanist").get()
            add("implementation", accompanist)

            val appcompat = libs.findLibrary("appcompat").get()
            add("implementation", appcompat)

            val material = libs.findLibrary("material").get()
            add("implementation", material)

            val lifecycle = libs.findBundle("lifecycle").get()
            add("implementation", lifecycle)

            val koinCompose = libs.findLibrary("koin-androidx-compose").get()
            add("implementation", koinCompose)
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs //+ buildComposeMetricsParameters()
        }
    }
}

// TODO Check
//private fun Project.buildComposeMetricsParameters(): List<String> {
//    val metricParameters = mutableListOf<String>()
//    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
//    val enableMetrics = (enableMetricsProvider.orNull == "true")
//    if (enableMetrics) {
//        val metricsFolder = File(project.buildDir, "compose-metrics")
//        metricParameters.add("-P")
//        metricParameters.add(
//            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
//        )
//    }
//
//    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
//    val enableReports = (enableReportsProvider.orNull == "true")
//    if (enableReports) {
//        val reportsFolder = File(project.buildDir, "compose-reports")
//        metricParameters.add("-P")
//        metricParameters.add(
//            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
//        )
//    }
//    return metricParameters.toList()
//}