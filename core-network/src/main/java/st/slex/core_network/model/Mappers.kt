package st.slex.core_network.model

import kotlinx.collections.immutable.toImmutableList
import st.slex.core_network.model.remote.collection.RemoteCollectionModel
import st.slex.core_network.model.remote.collection.RemoteLinksCollectionModel
import st.slex.core_network.model.remote.download.RemoteDownloadModel
import st.slex.core_network.model.remote.image.RemoteExifModel
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.model.remote.image.RemoteLinksImageModel
import st.slex.core_network.model.remote.image.RemoteLocationModel
import st.slex.core_network.model.remote.image.RemotePositionModel
import st.slex.core_network.model.remote.image.RemoteSponsorship
import st.slex.core_network.model.remote.image.RemoteTagModel
import st.slex.core_network.model.remote.image.RemoteUrlsModel
import st.slex.core_network.model.remote.statistic.RemoteDownloads
import st.slex.core_network.model.remote.statistic.RemoteHistorical
import st.slex.core_network.model.remote.statistic.RemoteLikes
import st.slex.core_network.model.remote.statistic.RemotePhotoStatistics
import st.slex.core_network.model.remote.statistic.RemoteValue
import st.slex.core_network.model.remote.statistic.RemoteViews
import st.slex.core_network.model.remote.user.RemoteBadgeModel
import st.slex.core_network.model.remote.user.RemoteProfileImageModel
import st.slex.core_network.model.remote.user.RemoteUserLinksModel
import st.slex.core_network.model.remote.user.RemoteUserModel
import st.slex.core_network.model.ui.CollectionDomainModel
import st.slex.core_network.model.ui.DownloadModel
import st.slex.core_network.model.ui.Downloads
import st.slex.core_network.model.ui.Historical
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.model.ui.Likes
import st.slex.core_network.model.ui.PhotoStatistics
import st.slex.core_network.model.ui.Value
import st.slex.core_network.model.ui.Views
import st.slex.core_network.model.ui.collection.LinksCollectionModel
import st.slex.core_network.model.ui.image.ExifModel
import st.slex.core_network.model.ui.image.LinksImageModel
import st.slex.core_network.model.ui.image.LocationModel
import st.slex.core_network.model.ui.image.PositionModel
import st.slex.core_network.model.ui.image.Sponsorship
import st.slex.core_network.model.ui.image.TagModel
import st.slex.core_network.model.ui.image.UrlsModel
import st.slex.core_network.model.ui.user.BadgeModel
import st.slex.core_network.model.ui.user.ProfileImageModel
import st.slex.core_network.model.ui.user.UserLinksModel
import st.slex.core_network.model.ui.user.UserModel

fun List<RemoteImageModel?>.map(): List<ImageModel> = map {
    it.map()
}

fun RemoteImageModel?.map(): ImageModel = ImageModel(
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
    exif = this?.exif.map(),
    location = this?.location.map(),
    tags = this?.tags?.map { it.map() }.orEmpty().toImmutableList(),
    currentUserCollections = this?.currentUserCollections?.map { it.map() }.orEmpty()
        .toImmutableList(),
    sponsorship = this?.sponsorship.map(),
    urls = this?.urls.map(),
    links = this?.links.map(),
    user = this?.user.map(),
    statistics = this?.statistics.map()
)

fun RemoteExifModel?.map(): ExifModel = ExifModel(
    make = this?.make.orEmpty(),
    model = this?.model.orEmpty(),
    exposureTime = this?.exposureTime.orEmpty(),
    aperture = this?.aperture.orEmpty(),
    focalLength = this?.focalLength.orEmpty(),
    iso = this?.iso ?: 0
)

fun RemoteLocationModel?.map(): LocationModel = LocationModel(
    city = this?.city.orEmpty(),
    country = this?.country.orEmpty(),
    position = this?.position.map()
)

fun RemoteTagModel?.map(): TagModel = TagModel(
    type = this?.type.orEmpty(),
    title = this?.title.orEmpty()
)

fun RemoteCollectionModel?.map(): CollectionDomainModel = CollectionDomainModel(
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
    tags = this?.tags?.map { it.map() }.orEmpty().toImmutableList(),
    coverPhoto = this?.coverPhoto.map(),
    previewPhotos = this?.previewPhotos?.map { it.map() }.orEmpty().toImmutableList(),
    user = this?.user.map(),
    links = this?.links.map()
)

fun RemoteSponsorship?.map(): Sponsorship = Sponsorship(sponsor = this?.sponsor.map())

fun RemoteUrlsModel?.map(): UrlsModel = UrlsModel(
    raw = this?.raw.orEmpty(),
    full = this?.full.orEmpty(),
    regular = this?.regular.orEmpty(),
    small = this?.small.orEmpty(),
    thumb = this?.thumb.orEmpty()
)

fun RemoteLinksImageModel?.map(): LinksImageModel = LinksImageModel(
    self = this?.self.orEmpty(),
    html = this?.html.orEmpty(),
    download = this?.download.orEmpty(),
    downloadLocation = this?.downloadLocation.orEmpty()
)

fun RemoteUserModel?.map(): UserModel =
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
        profileImageModel = this?.profileImage.map(),
        badge = this?.badge.map(),
        links = this?.links.map(),
        photos = this?.photos?.map { it.map() }.orEmpty()
    )

fun RemotePhotoStatistics?.map(): PhotoStatistics = PhotoStatistics(
    id = this?.id.orEmpty(),
    downloads = this?.downloads.map(),
    views = this?.views.map(),
    likes = this?.likes.map()
)

fun RemotePositionModel?.map(): PositionModel = PositionModel(
    latitude = this?.latitude ?: 0.0,
    longitude = this?.longitude ?: 0.0
)

fun RemoteLinksCollectionModel?.map(): LinksCollectionModel = LinksCollectionModel(
    self = this?.self.orEmpty(),
    html = this?.html.orEmpty(),
    photos = this?.photos.orEmpty()
)

fun RemoteProfileImageModel?.map(): ProfileImageModel = ProfileImageModel(
    small = this?.small.orEmpty(),
    medium = this?.medium.orEmpty(),
    large = this?.large.orEmpty()
)

fun RemoteBadgeModel?.map(): BadgeModel = BadgeModel(
    title = this?.title.orEmpty(),
    primary = this?.primary ?: false,
    slug = this?.slug.orEmpty(),
    link = this?.link.orEmpty()
)

fun RemoteUserLinksModel?.map(): UserLinksModel = UserLinksModel(
    self = this?.self.orEmpty(),
    html = this?.html.orEmpty(),
    photos = this?.photos.orEmpty(),
    likes = this?.likes.orEmpty(),
    portfolio = this?.portfolio.orEmpty(),
    following = this?.following.orEmpty(),
    followers = this?.followers.orEmpty()
)

fun RemoteDownloads?.map(): Downloads = Downloads(
    total = this?.total ?: 0,
    historical = this?.historical.map()
)

fun RemoteViews?.map(): Views = Views(
    total = this?.total ?: 0,
    historical = this?.historical.map()
)

fun RemoteLikes?.map(): Likes = Likes(
    total = this?.total ?: 0,
    historical = this?.historical.map()
)

fun RemoteHistorical?.map(): Historical = Historical(
    change = this?.change ?: 0,
    resolution = this?.resolution.orEmpty(),
    quality = this?.quality.orEmpty(),
    values = this?.values?.map { it.map() }.orEmpty()
)

fun RemoteValue?.map(): Value = Value(
    date = this?.date.orEmpty(),
    value = this?.value ?: 0
)

fun RemoteDownloadModel?.map(): DownloadModel = DownloadModel(url = this?.url.orEmpty())