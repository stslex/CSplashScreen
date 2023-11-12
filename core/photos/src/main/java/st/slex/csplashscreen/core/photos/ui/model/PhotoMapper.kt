package st.slex.csplashscreen.core.photos.ui.model

import st.slex.csplashscreen.core.network.model.ui.ImageModel
import st.slex.csplashscreen.core.network.model.ui.image.UrlsModel

fun ImageModel.toPresentation(): PhotoModel = PhotoModel(
    uuid = uuid,
    urls = urls.toPresentation(),
    username = user.username,
    userUrl = user.profileImageModel.medium,
    downloadUrl = this.links.downloadLocation,
    tags = this.tags.map { tag -> tag.title },
)

fun UrlsModel.toPresentation() = Urls(
    raw = raw,
    full = full,
    regular = regular,
    small = small,
    thumb = thumb
)