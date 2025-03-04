package nl.apperium.posts

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import nl.apperium.posts.core.data.constant.CONFIG_BUILD_TYPE_RELEASE
import timber.log.Timber

@HiltAndroidApp
class ApperiumApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Plant Tree for Timber logging in debug and beta builds
        if (BuildConfig.BUILD_TYPE != CONFIG_BUILD_TYPE_RELEASE) {
            Timber.plant(Timber.DebugTree())
        }

    }

    companion object {
        lateinit var instance: ApperiumApplication
            private set
    }
}
