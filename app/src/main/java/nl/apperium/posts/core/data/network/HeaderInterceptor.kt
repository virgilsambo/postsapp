package nl.apperium.posts.core.data.network

import okhttp3.Interceptor
import okhttp3.Response



class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
//                .addHeader("example", "example_value")
                .build()
        )
    }
}