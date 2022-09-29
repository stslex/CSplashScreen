package st.slex.feature_main

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
import st.slex.core_network.model.remote.topics.RemotePreviewPhotosModel
import st.slex.core_network.model.remote.topics.RemoteTopicsModel
import st.slex.core_network.model.remote.user.RemoteBadgeModel
import st.slex.core_network.model.remote.user.RemoteProfileImageModel
import st.slex.core_network.model.remote.user.RemoteUserLinksModel
import st.slex.core_network.model.remote.user.RemoteUserModel
import st.slex.core_network.model.ui.DownloadModel
import st.slex.core_network.model.ui.Downloads
import st.slex.core_network.model.ui.Historical
import st.slex.core_network.model.ui.Likes
import st.slex.core_network.model.ui.PhotoStatistics
import st.slex.core_network.model.ui.Value
import st.slex.core_network.model.ui.Views
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_network.model.ui.collection.LinksCollectionModel
import st.slex.core_network.model.ui.image.ExifModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_network.model.ui.image.LinksImageModel
import st.slex.core_network.model.ui.image.LocationModel
import st.slex.core_network.model.ui.image.PositionModel
import st.slex.core_network.model.ui.image.Sponsorship
import st.slex.core_network.model.ui.image.TagModel
import st.slex.core_network.model.ui.image.UrlsModel
import st.slex.core_network.model.ui.topics.PreviewPhotosModel
import st.slex.core_network.model.ui.topics.TopicsModel
import st.slex.core_network.model.ui.user.BadgeModel
import st.slex.core_network.model.ui.user.ProfileImageModel
import st.slex.core_network.model.ui.user.UserLinksModel
import st.slex.core_network.model.ui.user.UserModel

fun RemoteImageModel?.map(): ImageModel = ImageModel(
    id = this?.id ?: "",
    created_at = this?.created_at ?: "",
    updated_at = this?.updated_at ?: "",
    width = this?.width ?: 0,
    height = this?.height ?: 0,
    color = this?.color ?: "",
    blur_hash = this?.blur_hash ?: "",
    views = this?.views ?: 0,
    downloads = this?.downloads ?: 0,
    likes = this?.likes ?: 0,
    liked_by_user = this?.liked_by_user ?: false,
    description = this?.description ?: "",
    alt_description = this?.alt_description ?: "",
    exif = this?.exif.map(),
    location = this?.location.map(),
    tags = this?.tags?.map { it.map() } ?: emptyList(),
    current_user_collections = this?.current_user_collections?.map { it.map() } ?: emptyList(),
    sponsorship = this?.sponsorship.map(),
    urls = this?.urls.map(),
    links = this?.links.map(),
    user = this?.user.map(),
    statistics = this?.statistics.map()
)

fun RemoteExifModel?.map(): ExifModel = ExifModel(
    make = this?.make ?: "",
    model = this?.model ?: "",
    exposure_time = this?.exposure_time ?: "",
    aperture = this?.aperture ?: "",
    focal_length = this?.focal_length ?: "",
    iso = this?.iso ?: 0
)

fun RemoteLocationModel?.map(): LocationModel = LocationModel(
    city = this?.city ?: "",
    country = this?.country ?: "",
    position = this?.position.map()
)

fun RemoteTagModel?.map(): TagModel =
    TagModel(type = this?.type ?: "", title = this?.title ?: "")

fun RemoteCollectionModel?.map(): CollectionModel = CollectionModel(
    id = this?.id ?: "",
    title = this?.title ?: "",
    description = this?.description ?: "",
    published_at = this?.published_at ?: "",
    updated_at = this?.updated_at ?: "",
    curated = this?.curated ?: false,
    featured = this?.featured ?: false,
    total_photos = this?.total_photos ?: 0,
    private = this?.private ?: false,
    share_key = this?.share_key ?: "",
    tags = this?.tags?.map { it.map() } ?: emptyList(),
    cover_photo = this?.cover_photo.map(),
    preview_photos = this?.preview_photos?.map { it.map() } ?: emptyList(),
    user = this?.user.map(),
    links = this?.links.map()
)

fun RemoteSponsorship?.map(): Sponsorship = Sponsorship(sponsor = this?.sponsor.map())

fun RemoteUrlsModel?.map(): UrlsModel = UrlsModel(
    raw = this?.raw ?: "",
    full = this?.full ?: "",
    regular = this?.regular ?: "",
    small = this?.small ?: "",
    thumb = this?.thumb ?: ""
)

fun RemoteLinksImageModel?.map(): LinksImageModel = LinksImageModel(
    self = this?.self ?: "",
    html = this?.html ?: "",
    download = this?.download ?: "",
    download_location = this?.download_location ?: ""
)

fun RemoteUserModel?.map(): UserModel =
    UserModel(
        id = this?.id ?: "",
        updated_at = this?.updated_at ?: "",
        username = this?.username ?: "",
        name = this?.name ?: "",
        first_name = this?.first_name ?: "",
        last_name = this?.last_name ?: "",
        instagram_username = this?.instagram_username ?: "",
        twitter_username = this?.twitter_username ?: "",
        portfolio_url = this?.portfolio_url ?: "",
        bio = this?.bio ?: "",
        location = this?.location ?: "",
        total_likes = this?.total_likes ?: 0,
        total_photos = this?.total_photos ?: 0,
        total_collections = this?.total_collections ?: 0,
        followed_by_user = this?.followed_by_user ?: false,
        followers_count = this?.followers_count ?: 0,
        following_count = this?.following_count ?: 0,
        downloads = this?.downloads ?: 0,
        profile_image = this?.profile_image.map(),
        badge = this?.badge.map(),
        links = this?.links.map(),
        photos = this?.photos?.map { it.map() } ?: emptyList()
    )

fun RemotePhotoStatistics?.map(): PhotoStatistics = PhotoStatistics(
    id = this?.id ?: "",
    downloads = this?.downloads.map(),
    views = this?.views.map(),
    likes = this?.likes.map()
)

fun RemotePositionModel?.map(): PositionModel = PositionModel(
    latitude = this?.latitude ?: 0.0,
    longitude = this?.longitude ?: 0.0
)

fun RemoteLinksCollectionModel?.map(): LinksCollectionModel = LinksCollectionModel(
    self = this?.self ?: "",
    html = this?.html ?: "",
    photos = this?.photos ?: ""
)

fun RemoteProfileImageModel?.map(): ProfileImageModel = ProfileImageModel(
    small = this?.small ?: "",
    medium = this?.medium ?: "",
    large = this?.large ?: ""
)

fun RemoteBadgeModel?.map(): BadgeModel = BadgeModel(
    title = this?.title ?: "",
    primary = this?.primary ?: false,
    slug = this?.slug ?: "",
    link = this?.link ?: ""
)

fun RemoteUserLinksModel?.map(): UserLinksModel = UserLinksModel(
    self = this?.self ?: "",
    html = this?.html ?: "",
    photos = this?.photos ?: "",
    likes = this?.likes ?: "",
    portfolio = this?.portfolio ?: "",
    following = this?.following ?: "",
    followers = this?.followers ?: ""
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
    resolution = this?.resolution ?: "",
    quality = this?.quality ?: "",
    values = this?.values?.map { it.map() } ?: emptyList()
)

fun RemoteValue?.map(): Value = Value(
    date = this?.date ?: "",
    value = this?.value ?: 0
)

fun RemoteDownloadModel?.map(): DownloadModel = DownloadModel(url = this?.url ?: "")

fun RemoteTopicsModel?.map(): TopicsModel = TopicsModel(
    id = this?.id ?: "",
    slug = this?.slug ?: "",
    title = this?.title ?: "",
    description = this?.description ?: "",
    published_at = this?.published_at ?: "",
    updated_at = this?.updated_at ?: "",
    starts_at = this?.starts_at ?: "",
    ends_at = this?.ends_at ?: "",
    only_submissions_after = this?.only_submissions_after ?: "",
    featured = this?.featured ?: "",
    total_photos = this?.total_photos ?: "",
    links = this?.links.map(),
    status = this?.status ?: "",
    owners = this?.owners?.map { it.map() } ?: emptyList(),
    cover_photo = this?.cover_photo.map(),
    preview_photos = this?.preview_photos?.map { it.toPreviewPhotosModel() } ?: emptyList()
)

fun RemotePreviewPhotosModel?.toPreviewPhotosModel() = PreviewPhotosModel(
    id = this?.id ?: "",
    created_at = this?.created_at ?: "",
    updated_at = this?.updated_at ?: "",
    urls = this?.urls.map()
)