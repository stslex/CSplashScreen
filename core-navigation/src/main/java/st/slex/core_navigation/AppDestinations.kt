package st.slex.core_navigation

abstract class AppDestinations(
    private val name: String
) {
    val route: String
        get() = "${name}route"

    val destination: String
        get() = "${name}destination"

    object Home : AppDestinations("home")
}