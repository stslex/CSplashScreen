import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

object AppVersions {
    const val VERSION_NAME = "1.71"
    const val VERSION_CODE = 17
}

object AppExt {

    /**
     * Get the version catalog for the project
     * */
    val Project.currentLibs: VersionCatalog
        get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

    /**
     * Find the version of the library
     */
    fun VersionCatalog.findVersionInt(name: String) = findVersionString(name).toInt()

    /**
     * Find the version of the library
     */
    fun VersionCatalog.findVersionString(name: String) = findVersion(name).get().toString()
}