package nl.apperium.posts.core.domain.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionManager @Inject constructor(
    @ApplicationContext context: Context,
) {
    var hasNetworkConnection = false

    fun noInternet(): Boolean {
        if (!hasNetworkConnection) BannerManager.showNoInternetBanner()

        return !hasNetworkConnection
    }

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            hasNetworkConnection = true
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            hasNetworkConnection = false
        }
    }

    private val connectivityManager = context.getSystemService(
        ConnectivityManager::class.java
    ) as ConnectivityManager

    init {
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }
}