package st.slex.feature_topics.data.model

import st.slex.core_network.model.map
import st.slex.core_network.model.remote.topics.RemotePreviewPhotosModel
import st.slex.core_network.model.remote.topics.RemoteTopicsModel

fun RemoteTopicsModel.toTopicsModel(): TopicsModel = TopicsModel(
    id = id.orEmpty(),
    slug = slug.orEmpty(),
    title = title.orEmpty(),
    description = description.orEmpty(),
    publishedAt = publishedAt.orEmpty(),
    updatedAt = updatedAt.orEmpty(),
    startsAt = startsAt.orEmpty(),
    endsAt = endsAt.orEmpty(),
    onlySubmissionsAfter = onlySubmissionsAfter.orEmpty(),
    featured = featured.orEmpty(),
    totalPhotos = totalPhotos.orEmpty(),
    links = links.map(),
    status = status.orEmpty(),
    owners = owners?.map { it.map() }.orEmpty(),
    coverPhoto = coverPhoto.map(),
    previewPhotos = previewPhotos?.map { it.toPreviewPhotosModel() }.orEmpty()
)

fun RemotePreviewPhotosModel.toPreviewPhotosModel() = PreviewPhotosModel(
    id = id.orEmpty(),
    createdAt = createdAt.orEmpty(),
    updatedAt = updatedAt.orEmpty(),
    urls = urls.map()
)