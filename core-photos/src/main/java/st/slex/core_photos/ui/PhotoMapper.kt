package st.slex.core_photos.ui

import st.slex.core_network.model.ui.ImageModel

fun ImageModel.toPresentation(): PhotoModel = PhotoModel(
    uuid = uuid,
    url = urls.regular,
    username = user.username,
    userUrl = user.profileImageModel.medium,
)