package st.slex.csplashscreen.core.network.source.interf

import st.slex.csplashscreen.core.network.model.remote.topics.RemoteTopicsModel

interface TopicsNetworkSource {

    suspend fun getTopics(
        page: Int,
        pageSize: Int
    ): List<RemoteTopicsModel>
}