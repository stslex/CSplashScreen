package st.slex.csplashscreen.data.core

import st.slex.csplashscreen.BuildConfig

object Constants {
    /*API_KEY*/
    const val API_KEY = BuildConfig.API_KEY

    /*BASE_URL*/
    const val BASE_URL = "https://api.unsplash.com/"

    /*GET*/
    const val GET_PHOTOS = "photos"
    const val GET_COLLECTIONS = "collections"
    const val GET_USERS = "users"
    const val GET_DOWNLOAD = "download"
    const val GET_LIKES = "likes"
    const val GET_TOPICS = "topics"
    const val GET_SEARCH = "search"

    /*QUERY*/
    const val QUERY_API_KEY = "client_id"
    const val QUERY_PAGE = "page"
    const val QUERY_PAGE_SIZE = "per_page"
    const val QUERY = "query"
}