package st.slex.core_network.source.interf

import st.slex.core_network.model.remote.topics.RemoteTopicsModel

interface TopicsNetworkSource {
    suspend fun getTopics(page: Int): List<RemoteTopicsModel>
}