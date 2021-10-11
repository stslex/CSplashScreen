package st.slex.csplashscreen.data.core

import st.slex.csplashscreen.data.model.remote.collection.RemoteCollectionModel
import st.slex.csplashscreen.data.model.remote.collection.RemoteLinksCollectionModel
import st.slex.csplashscreen.data.model.remote.download.RemoteDownloadModel
import st.slex.csplashscreen.data.model.remote.image.*
import st.slex.csplashscreen.data.model.remote.statistic.*
import st.slex.csplashscreen.data.model.remote.topics.RemotePreviewPhotosModel
import st.slex.csplashscreen.data.model.remote.topics.RemoteTopicsModel
import st.slex.csplashscreen.data.model.remote.user.RemoteBadgeModel
import st.slex.csplashscreen.data.model.remote.user.RemoteProfileImageModel
import st.slex.csplashscreen.data.model.remote.user.RemoteUserLinksModel
import st.slex.csplashscreen.data.model.remote.user.RemoteUserModel
import st.slex.csplashscreen.data.model.ui.*
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.collection.LinksCollectionModel
import st.slex.csplashscreen.data.model.ui.image.*
import st.slex.csplashscreen.data.model.ui.topics.PreviewPhotosModel
import st.slex.csplashscreen.data.model.ui.topics.TopicsModel
import st.slex.csplashscreen.data.model.ui.user.BadgeModel
import st.slex.csplashscreen.data.model.ui.user.ProfileImageModel
import st.slex.csplashscreen.data.model.ui.user.UserLinksModel
import st.slex.csplashscreen.data.model.ui.user.UserModel

internal fun RemoteImageModel.toImageModel(): ImageModel = ImageModel(
    id = id,
    created_at = created_at ?: "",
    updated_at = updated_at ?: "",
    width = width ?: 0,
    height = height ?: 0,
    color = color ?: "",
    blur_hash = blur_hash ?: "",
    views = views ?: 0,
    downloads = downloads ?: 0,
    likes = likes ?: 0,
    liked_by_user = liked_by_user ?: false,
    description = description ?: "",
    alt_description = alt_description ?: "",
    exif = exif?.toExifModel(),
    location = location?.toLocationModel(),
    tags = tags?.map { it.toTagModel() },
    current_user_collections = current_user_collections?.map { it.toCollectionModel() },
    sponsorship = sponsorship?.toSponsorship(),
    urls = urls.toUrlsModel(),
    links = links?.toLinksImageModel(),
    user = user?.toUserModel(),
    statistics = statistics?.toPhotoStatistic()
)

internal fun RemoteExifModel.toExifModel(): ExifModel =
    ExifModel(
        make = make ?: "",
        model = model ?: "",
        exposure_time = exposure_time ?: "",
        aperture = aperture ?: "",
        focal_length = focal_length ?: "",
        iso = iso ?: 0
    )

internal fun RemoteLocationModel.toLocationModel(): LocationModel =
    LocationModel(
        city = city ?: "",
        country = country ?: "",
        position = position?.toPositionModel()
    )

internal fun RemoteTagModel.toTagModel(): TagModel =
    TagModel(type = type ?: "", title = title ?: "")

internal fun RemoteCollectionModel.toCollectionModel(): CollectionModel =
    CollectionModel(
        id = id,
        title = title,
        description = description ?: "",
        published_at = published_at ?: "",
        updated_at = updated_at ?: "",
        curated = curated ?: false,
        featured = featured ?: false,
        total_photos = total_photos,
        private = private ?: false,
        share_key = share_key ?: "",
        tags = tags?.map { it.toTagModel() },
        cover_photo = cover_photo?.toImageModel(),
        preview_photos = preview_photos?.map { it.toImageModel() },
        user = user?.toUserModel(),
        links = links?.toLinksCollectionModel()
    )

internal fun RemoteSponsorship.toSponsorship(): Sponsorship =
    Sponsorship(sponsor = sponsor?.toUserModel())

internal fun RemoteUrlsModel.toUrlsModel(): UrlsModel =
    UrlsModel(
        raw = raw,
        full = full,
        regular = regular,
        small = small,
        thumb = thumb
    )

internal fun RemoteLinksImageModel.toLinksImageModel(): LinksImageModel =
    LinksImageModel(
        self = self,
        html = html,
        download = download,
        download_location = download_location
    )

internal fun RemoteUserModel.toUserModel(): UserModel =
    UserModel(
        id = id,
        updated_at = updated_at ?: "",
        username = username ?: "",
        name = name ?: "",
        first_name = first_name ?: "",
        last_name = last_name ?: "",
        instagram_username = instagram_username ?: "",
        twitter_username = twitter_username ?: "",
        portfolio_url = portfolio_url ?: "",
        bio = bio ?: "",
        location = location ?: "",
        total_likes = total_likes ?: 0,
        total_photos = total_photos ?: 0,
        total_collections = total_collections ?: 0,
        followed_by_user = followed_by_user ?: false,
        followers_count = followers_count ?: 0,
        following_count = following_count ?: 0,
        downloads = downloads ?: 0,
        profile_image = profile_image?.toProfileImageModel(),
        badge = badge?.toBadgeModel(),
        links = links?.toUserLinksModel(),
        photos = photos?.map { it.toImageModel() }
    )

internal fun RemotePhotoStatistics.toPhotoStatistic(): PhotoStatistics =
    PhotoStatistics(
        id = id,
        downloads = downloads.toDownloads(),
        views = views.toViews(),
        likes = likes.toLikes()
    )

internal fun RemotePositionModel.toPositionModel(): PositionModel =
    PositionModel(latitude = latitude ?: 0.0, longitude = longitude ?: 0.0)

internal fun RemoteLinksCollectionModel.toLinksCollectionModel(): LinksCollectionModel =
    LinksCollectionModel(self = self, html = html, photos = photos)

internal fun RemoteProfileImageModel.toProfileImageModel(): ProfileImageModel =
    ProfileImageModel(small = small, medium = medium, large = large)

internal fun RemoteBadgeModel.toBadgeModel(): BadgeModel =
    BadgeModel(title = title ?: "", primary = primary ?: false, slug = slug ?: "", link = link)

internal fun RemoteUserLinksModel.toUserLinksModel(): UserLinksModel =
    UserLinksModel(
        self = self,
        html = html,
        photos = photos,
        likes = likes,
        portfolio = portfolio,
        following = following,
        followers = followers
    )

internal fun RemoteDownloads.toDownloads(): Downloads =
    Downloads(total = total, historical = historical.toHistorical())

internal fun RemoteViews.toViews(): Views =
    Views(total = total, historical = historical.toHistorical())

internal fun RemoteLikes.toLikes(): Likes =
    Likes(total = total, historical = historical.toHistorical())

internal fun RemoteHistorical.toHistorical(): Historical =
    Historical(
        change = change,
        resolution = resolution,
        quality = quality,
        values = values.map { it.toValue() }
    )

internal fun RemoteValue.toValue(): Value = Value(date = date, value = value)

internal fun RemoteDownloadModel.toDownloadModel(): DownloadModel = DownloadModel(url = url)

internal fun RemoteTopicsModel.toTopicsModel(): TopicsModel =
    TopicsModel(
        id = id.toString(),
        slug = slug.toString(),
        title = title.toString(),
        description = description.toString(),
        published_at = published_at.toString(),
        updated_at = updated_at.toString(),
        starts_at = starts_at.toString(),
        ends_at = ends_at.toString(),
        only_submissions_after = only_submissions_after.toString(),
        featured = featured.toString(),
        total_photos = total_photos.toString(),
        links = links.toLinksCollectionModel(),
        status = status.toString(),
        owners = owners?.map { it.toUserModel() },
        cover_photo = cover_photo?.toImageModel(),
        preview_photos = preview_photos?.map { it.toPreviewPhotosModel() }
    )

internal fun RemotePreviewPhotosModel.toPreviewPhotosModel() =
    PreviewPhotosModel(
        id = id.toString(),
        created_at = created_at.toString(),
        updated_at = updated_at.toString(),
        urls = urls?.toUrlsModel()
    )