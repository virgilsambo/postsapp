package nl.apperium.posts.core.presentation.uitstate

import nl.apperium.posts.core.domain.model.Banner

data class BannerState(
    val showBanner: Boolean = false,
    val banner: Banner = Banner(),
)
