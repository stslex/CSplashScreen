package st.slex.core_network.service

import st.slex.core.BuildConfig

 object ServiceConstants {
    const val API_KEY = BuildConfig.API_KEY

    const val GET_PHOTOS = "photos"
    const val GET_COLLECTIONS = "collections"
    const val GET_USERS = "users"
    const val GET_DOWNLOAD = "download"
    const val GET_LIKES = "likes"
    const val GET_TOPICS = "topics"
    const val GET_SEARCH = "search"

    const val QUERY_API_KEY = "client_id"
    const val QUERY_PAGE = "page"
    const val QUERY_PAGE_SIZE = "per_page"
    const val QUERY = "query"
}