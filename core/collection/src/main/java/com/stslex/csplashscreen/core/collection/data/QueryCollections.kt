package com.stslex.csplashscreen.core.collection.data

sealed class QueryCollections {
    class UserCollections(val query: String) : QueryCollections()
    object AllCollections : QueryCollections()
    object EmptyQuery : QueryCollections()
}
