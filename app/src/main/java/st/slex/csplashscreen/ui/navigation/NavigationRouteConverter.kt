package st.slex.csplashscreen.ui.navigation

import androidx.navigation.NavBackStackEntry

object NavigationRouteConverter {

    fun NavBackStackEntry.convertArgs(args: List<String>): List<String> =
        args.map { arguments?.getString(it).toString() }

    fun NavHostResource.convertRoute() = "$destination${arguments.convertHostArgumentsRoute()}"

    fun NavActions.convertRoute() =
        "$destination${arguments.values.toList().convertGetArgumentsRoute()}"

    private fun List<String>.convertHostArgumentsRoute() = if (!isNullOrEmpty()) {
        joinToString(separator = "}/{", prefix = "/{", postfix = "}")
    } else ""

    private fun List<String>.convertGetArgumentsRoute() = if (!isNullOrEmpty()) {
        joinToString(separator = "/", prefix = "/")
    } else ""
}