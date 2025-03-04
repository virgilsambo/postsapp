package nl.apperium.posts.core.domain.exception

import nl.apperium.posts.R
import nl.apperium.posts.core.domain.model.UiText

sealed class NetworkException(open val text: UiText) : Exception() {
    object EmptyResponseBodyException :
        NetworkException(text = UiText.StringResource(R.string.error_empty_response_body))

    object NoNetworkAvailableException :
        NetworkException(text = UiText.StringResource(R.string.error_no_internet_connection))

    object RequestUnsuccessfulException :
        NetworkException(text = UiText.StringResource(R.string.error_request_unsuccessful))

    class UnknownApiErrorException(override val text: UiText = UiText.StringResource(R.string.error_unknown)) :
        NetworkException(text = text)
}


