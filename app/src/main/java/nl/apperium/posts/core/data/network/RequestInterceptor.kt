package nl.apperium.posts.core.data.network

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val newUrl = request().url
            .newBuilder()
//            .addQueryParameter("example_query_name", "example value")
            .build()

        proceed(
            request()
                .newBuilder()
                .url(newUrl)
                .build()
        )
    }
}