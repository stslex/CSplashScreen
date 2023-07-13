package st.slex.feature_user.ui.components.tabs

enum class UserTabs(
    val index: Int,
    val title: String
) {
    PHOTOS(
        index = 0,
        title = "Photos"
    ),
    LIKE(
        index = 1,
        title = "Likes"
    ),
    COLLECTION(
        index = 2,
        title = "Collections"
    );

    companion object {

        fun getByIndex(index: Int) = values()
            .firstOrNull {
                it.index == index
            }
            ?: throw IllegalStateException()
    }
}