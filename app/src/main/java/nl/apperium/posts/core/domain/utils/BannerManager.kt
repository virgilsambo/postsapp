package nl.apperium.posts.core.domain.utils

import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.apperium.posts.R
import nl.apperium.posts.core.domain.model.Banner
import nl.apperium.posts.core.domain.model.UiText
import nl.apperium.posts.core.presentation.uitstate.BannerState
import kotlin.time.Duration.Companion.seconds

object BannerManager {
    private var bannerJob: Job? = null

    private val scope = CoroutineScope(Dispatchers.Main)

    private val _bannerState = MutableStateFlow(BannerState())
    val bannerState = _bannerState.asStateFlow()

    fun showErrorBanner(text: UiText) {
        showBanner(Banner(message = text, color = Blue, textColor = White))
    }

    fun showMessageBanner(text: UiText) {
        showBanner(Banner(message = text, color = Green, textColor = White))
    }

    fun showNoInternetBanner() {
        showErrorBanner(UiText.StringResource(R.string.error_no_internet_connection))
    }

    fun showSomethingWentWrongBanner() {
        showErrorBanner(UiText.StringResource(R.string.something_went_wrong))
    }

    private fun showBanner(banner: Banner) {
        setBanner(banner)

        bannerJob?.cancel()

        bannerJob = scope.launch {
            showBanner()
            delay(3.seconds)
            hideBanner()
        }
    }

    private fun showBanner() {
        _bannerState.update {
            it.copy(
                showBanner = true,
            )
        }
    }

    fun hideBanner() {
        _bannerState.update {
            it.copy(
                showBanner = false,
            )
        }
    }

    private fun setBanner(banner: Banner) {
        _bannerState.update {
            it.copy(
                showBanner = false,
                banner = banner
            )
        }
    }
}