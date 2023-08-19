package st.slex.csplashscreen.core.network.model

import kotlinx.collections.immutable.toImmutableList
import st.slex.csplashscreen.core.network.model.remote.collection.RemoteCollectionModel
import st.slex.csplashscreen.core.network.model.remote.collection.RemoteLinksCollectionModel
import st.slex.csplashscreen.core.network.model.remote.download.RemoteDownloadModel
import st.slex.csplashscreen.core.network.model.remote.image.RemoteExifModel
import st.slex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.core.network.model.remote.image.RemoteLinksImageModel
import st.slex.csplashscreen.core.network.model.remote.image.RemoteLocationModel
import st.slex.csplashscreen.core.network.model.remote.image.RemotePositionModel
import st.slex.csplashscreen.core.network.model.remote.image.RemoteSponsorship
import st.slex.csplashscreen.core.network.model.remote.image.RemoteTagModel
import st.slex.csplashscreen.core.network.model.remote.image.RemoteUrlsModel
import st.slex.csplashscreen.core.network.model.remote.statistic.RemoteDownloads
import st.slex.csplashscreen.core.network.model.remote.statistic.RemoteHistorical
import st.slex.csplashscreen.core.network.model.remote.statistic.RemoteLikes
import st.slex.csplashscreen.core.network.model.remote.statistic.RemotePhotoStatistics
import st.slex.csplashscreen.core.network.model.remote.statistic.RemoteValue
import st.slex.csplashscreen.core.network.model.remote.statistic.RemoteViews
import st.slex.csplashscreen.core.network.model.remote.user.RemoteBadgeModel
import st.slex.csplashscreen.core.network.model.remote.user.RemoteProfileImageModel
import st.slex.csplashscreen.core.network.model.remote.user.RemoteUserLinksModel
import st.slex.csplashscreen.core.network.model.remote.user.RemoteUserModel
import st.slex.csplashscreen.core.network.model.ui.CollectionDomainModel
import st.slex.csplashscreen.core.network.model.ui.DownloadModel
import st.slex.csplashscreen.core.network.model.ui.Downloads
import st.slex.csplashscreen.core.network.model.ui.Historical
import st.slex.csplashscreen.core.network.model.ui.ImageModel
import st.slex.csplashscreen.core.network.model.ui.Likes
import st.slex.csplashscreen.core.network.model.ui.PhotoStatistics
import st.slex.csplashscreen.core.network.model.ui.Value
import st.slex.csplashscreen.core.network.model.ui.Views
import st.slex.csplashscreen.core.network.model.ui.collection.LinksCollectionModel
import st.slex.csplashscreen.core.network.model.ui.image.ExifModel
import st.slex.csplashscreen.core.network.model.ui.image.LinksImageModel
import st.slex.csplashscreen.core.network.model.ui.image.LocationModel
import st.slex.csplashscreen.core.network.model.ui.image.PositionModel
import st.slex.csplashscreen.core.network.model.ui.image.Sponsorship
import st.slex.csplashscreen.core.network.model.ui.image.TagModel
import st.slex.csplashscreen.core.network.model.ui.image.UrlsModel
import st.slex.csplashscreen.core.network.model.ui.user.BadgeModel
import st.slex.csplashscreen.core.network.model.ui.user.ProfileImageModel
import st.slex.csplashscreen.core.network.model.ui.user.UserLinksModel
import st.slex.csplashscreen.core.network.model.ui.user.UserModel

fun List<RemoteImageModel?>.toDomain(): List<ImageModel> = map {
    it.toDomain()
}

fun List<RemoteCollectionModel?>.mapToDomain(): List<CollectionDomainModel> = map {
    it.toDomain()
}

fun RemoteImageModel?.toDomain(): ImageModel = ImageModel(
    uuid = this?.id.orEmpty(),
    createdAt = this?.createdAt.orEmpty(),
    updatedAt = this?.updatedAt.orEmpty(),
    width = this?.width ?: 0,
    height = this?.height ?: 0,
    color = this?.color.orEmpty(),
    blurHash = this?.blurHash.orEmpty(),
    views = this?.views ?: 0,
    downloads = this?.downloads ?: 0,
    likes = this?.likes ?: 0,
    likedByUser = this?.likedByUser ?: false,
    description = this?.description.orEmpty(),
    altDescription = this?.altDescription.orEmpty(),
    exif = this?.exif.toDomain(),
    location = this?.location.toDomain(),
    tags = this?.tags?.map { it.toDomain() }.orEmpty().toImmutableList(),
    currentUserCollections = this?.currentUserCollections?.map { it.toDomain() }.orEmpty()
        .toImmutableList(),
    sponsorship = this?.sponsorship.toDomain(),
    urls = this?.urls.toDomain(),
    links = this?.links.toDomain(),
    user = this?.user.toDomain(),
    statistics = this?.statistics.toDomain()
)

fun RemoteExifModel?.toDomain(): ExifModel = ExifModel(
    make = this?.make.orEmpty(),
    model = this?.model.orEmpty(),
    exposureTime = this?.exposureTime.orEmpty(),
    aperture = this?.aperture.orEmpty(),
    focalLength = this?.focalLength.orEmpty(),
    iso = this?.iso ?: 0
)

fun RemoteLocationModel?.toDomain(): LocationModel = LocationModel(
    city = this?.city.orEmpty(),
    country = this?.country.orEmpty(),
    position = this?.position.toDomain()
)

fun RemoteTagModel?.toDomain(): TagModel = TagModel(
    type = this?.type.orEmpty(),
    title = this?.title.orEmpty()
)

fun RemoteCollectionModel?.toDomain(): CollectionDomainModel = CollectionDomainModel(
    uuid = this?.id.orEmpty(),
    title = this?.title.orEmpty(),
    description = this?.description.orEmpty(),
    publishedAt = this?.publishedAt.orEmpty(),
    updatedAt = this?.updatedAt.orEmpty(),
    curated = this?.curated ?: false,
    featured = this?.featured ?: false,
    totalPhotos = this?.totalPhotos ?: 0,
    private = this?.private ?: false,
    shareKey = this?.shareKey.orEmpty(),
    tags = this?.tags?.map { it.toDomain() }.orEmpty().toImmutableList(),
    coverPhoto = this?.coverPhoto.toDomain(),
    previewPhotos = this?.previewPhotos?.map { it.toDomain() }.orEmpty().toImmutableList(),
    user = this?.user.toDomain(),
    links = this?.links.toDomain()
)

fun RemoteSponsorship?.toDomain(): Sponsorship = Sponsorship(sponsor = this?.sponsor.toDomain())

fun RemoteUrlsModel?.toDomain(): UrlsModel = UrlsModel(
    raw = this?.raw.orEmpty(),
    full = this?.full.orEmpty(),
    regular = this?.regular.orEmpty(),
    small = this?.small.orEmpty(),
    thumb = this?.thumb.orEmpty()
)

fun RemoteLinksImageModel?.toDomain(): LinksImageModel = LinksImageModel(
    self = this?.self.orEmpty(),
    html = this?.html.orEmpty(),
    download = this?.download.orEmpty(),
    downloadLocation = this?.downloadLocation.orEmpty()
)

fun RemoteUserModel?.toDomain(): UserModel =
    UserModel(
        id = this?.id.orEmpty(),
        updatedAt = this?.updatedAt.orEmpty(),
        username = this?.username.orEmpty(),
        name = this?.name.orEmpty(),
        firstName = this?.firstName.orEmpty(),
        lastName = this?.lastName.orEmpty(),
        instagramUsername = this?.instagramUsername.orEmpty(),
        twitterUsername = this?.twitterUsername.orEmpty(),
        portfolioUrl = this?.portfolioUrl.orEmpty(),
        bio = this?.bio.orEmpty(),
        location = this?.location.orEmpty(),
        totalLikes = this?.totalLikes ?: 0,
        totalPhotos = this?.totalPhotos ?: 0,
        totalCollections = this?.totalCollections ?: 0,
        followedByUser = this?.followedByUser ?: false,
        followersCount = this?.followersCount ?: 0,
        followingCount = this?.followingCount ?: 0,
        downloads = this?.downloads ?: 0,
        profileImageModel = this?.profileImage.toDomain(),
        badge = this?.badge.toDomain(),
        links = this?.links.toDomain(),
        photos = this?.photos?.map { it.toDomain() }.orEmpty()
    )

fun RemotePhotoStatistics?.toDomain(): PhotoStatistics = PhotoStatistics(
    id = this?.id.orEmpty(),
    downloads = this?.downloads.toDomain(),
    views = this?.views.toDomain(),
    likes = this?.likes.toDomain()
)

fun RemotePositionModel?.toDomain(): PositionModel = PositionModel(
    latitude = this?.latitude ?: 0.0,
    longitude = this?.longitude ?: 0.0
)

fun RemoteLinksCollectionModel?.toDomain(): LinksCollectionModel = LinksCollectionModel(
    self = this?.self.orEmpty(),
    html = this?.html.orEmpty(),
    photos = this?.photos.orEmpty()
)

fun RemoteProfileImageModel?.toDomain(): ProfileImageModel = ProfileImageModel(
    small = this?.small.orEmpty(),
    medium = this?.medium.orEmpty(),
    large = this?.large.orEmpty()
)

fun RemoteBadgeModel?.toDomain(): BadgeModel = BadgeModel(
    title = this?.title.orEmpty(),
    primary = this?.primary ?: false,
    slug = this?.slug.orEmpty(),
    link = this?.link.orEmpty()
)

fun RemoteUserLinksModel?.toDomain(): UserLinksModel = UserLinksModel(
    self = this?.self.orEmpty(),
    html = this?.html.orEmpty(),
    photos = this?.photos.orEmpty(),
    likes = this?.likes.orEmpty(),
    portfolio = this?.portfolio.orEmpty(),
    following = this?.following.orEmpty(),
    followers = this?.followers.orEmpty()
)

fun RemoteDownloads?.toDomain(): Downloads = Downloads(
    total = this?.total ?: 0,
    historical = this?.historical.toDomain()
)

fun RemoteViews?.toDomain(): Views = Views(
    total = this?.total ?: 0,
    historical = this?.historical.toDomain()
)

fun RemoteLikes?.toDomain(): Likes = Likes(
    total = this?.total ?: 0,
    historical = this?.historical.toDomain()
)

fun RemoteHistorical?.toDomain(): Historical = Historical(
    change = this?.change ?: 0,
    resolution = this?.resolution.orEmpty(),
    quality = this?.quality.orEmpty(),
    values = this?.values?.map { it.toDomain() }.orEmpty()
)

fun RemoteValue?.toDomain(): Value = Value(
    date = this?.date.orEmpty(),
    value = this?.value ?: 0
)

fun RemoteDownloadModel?.toDomain(): DownloadModel = DownloadModel(url = this?.url.orEmpty())