package com.khaled.thmanyah.core.utils

import com.khaled.thmanyah.core.utils.AppData.BASE_URL
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.URISyntaxException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HostSelectionInterceptor @Inject constructor() : Interceptor {


    @Volatile
    private var host = BASE_URL.toHttpUrlOrNull()

    init {
        setHostBaseUrl(BASE_URL.toHttpUrlOrNull().toString())
    }

    fun setHostBaseUrl(url: String) {
        host = url.toHttpUrlOrNull()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if (host == null) {
            setHostBaseUrl(BASE_URL.toHttpUrlOrNull().toString())
        }
        var newUrl: HttpUrl? = null
        try {
            newUrl = request.url.newBuilder()
                .build()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        request = request.newBuilder()
            .url(newUrl!!)
            .build()

        return chain.proceed(request)
    }
}
object AppData {
    const val BASE_URL: String = "https://api-v2-b2sit6oh3a-uc.a.run.app/"
}