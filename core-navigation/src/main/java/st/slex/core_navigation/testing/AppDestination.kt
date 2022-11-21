package st.slex.core_navigation.testing

enum class AppDestination(private vararg val argsNames: String) {
    HOME,
    IMAGE_DETAIL("url", "imageId"),
    COLLECTION("collection_id"),
    RAW_IMAGE("url"),
    SEARCH_PHOTOS("query"),
    USER("username"),
    TOPICS;

    val route: String
        get() = "$name$TAG_ROUTE"

    val destination: String
        get() = "$name$TAG_DESTINATION"

    val navigationRoute: String
        get() = "$route${argsNames.argumentsRoute}"

    private val Array<out String>.argumentsRoute: String
        get() = if (isEmpty()) {
            String()
        } else {
            joinToString(separator = "}/{", prefix = "/{", postfix = "}")
        }

    companion object {
        private const val TAG_ROUTE = "route"
        private const val TAG_DESTINATION = "destination"
    }
}