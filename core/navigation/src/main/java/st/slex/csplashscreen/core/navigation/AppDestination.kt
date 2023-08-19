package st.slex.csplashscreen.core.navigation

enum class AppDestination(vararg val argsNames: String) {
    HOME,
    IMAGE_DETAIL("imageId"),
    COLLECTION("collection_id"),
    SEARCH_PHOTOS("query"),
    FAVOURITE,
    USER("username"),
    UNDEFINED;

    val route: String
        get() = StringBuilder()
            .append(name, SEPARATOR_ROUTE, TAG_ROUTE)
            .toString()
            .lowercase()

    val navigationRoute: String
        get() = "$route${argsNames.argumentsRoute}"

    private val Array<out String>.argumentsRoute: String
        get() = if (isEmpty()) {
            String()
        } else {
            joinToString(separator = "}/{", prefix = "/{", postfix = "}")
        }

    companion object {
        private const val SEPARATOR_ROUTE = "_"
        private const val TAG_ROUTE = "route"

        fun findByRoute(route: String?) = AppDestination
            .entries
            .firstOrNull {
                it.route == route
            }
    }
}