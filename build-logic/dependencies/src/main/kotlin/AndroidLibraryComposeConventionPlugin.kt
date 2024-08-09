import AppExt.findPluginId
import AppExt.libs
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import st.slex.csplashscreen.configureAndroidCompose

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply(libs.findPluginId("kotlin"))
            pluginManager.apply(libs.findPluginId("composeCompiler"))
            pluginManager.apply(libs.findPluginId("vkompose"))

            configureAndroidCompose(
                commonExtension = extensions.getByType<LibraryExtension>()
            )
        }
    }
}