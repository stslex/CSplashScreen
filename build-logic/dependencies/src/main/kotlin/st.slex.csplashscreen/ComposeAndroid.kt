package st.slex.csplashscreen

import AppExt.androidTestImplementation
import AppExt.androidTestImplementationPlatform
import AppExt.debugImplementation
import AppExt.implementation
import AppExt.implementationBundle
import AppExt.implementationPlatform
import com.android.build.api.dsl.CommonExtension
import com.vk.gradle.plugin.compose.utils.VkomposeExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures.compose = true

        androidTestImplementationPlatform("androidx-compose-bom")
        implementationPlatform("androidx-compose-bom")
        debugImplementation("androidx-compose-tooling")
        androidTestImplementation("androidx-compose-ui-test-junit4")
        implementationBundle("accompanist", "compose", "lifecycle")
        implementation("appcompat", "material", "koin-androidx-compose")
    }

    extensions.configure<VkomposeExtension>(action = ::configureVkompose)
}

private fun Project.configureVkompose(
    extension: VkomposeExtension
): Unit = with(extension) {
    skippabilityCheck = true
    skippabilityCheck {
        // For more see
        // https://android-review.googlesource.com/c/platform/frameworks/support/+/2668595
        // https://issuetracker.google.com/issues/309765121
        stabilityConfigurationPath = "/path/file.config"
    }

    recompose {
        isHighlighterEnabled = true
        isLoggerEnabled = true
        // or
        // logger {
        //  logModifierChanges = true // true by default since 0.5
        //  logFunctionChanges = true // true by default since 0.5. log when function arguments (like lambdas or function references) of composable function are changed
        // }
    }

    testTag {
        isApplierEnabled = true
        isDrawerEnabled = false
        isCleanerEnabled = false
    }

    sourceInformationClean = true
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