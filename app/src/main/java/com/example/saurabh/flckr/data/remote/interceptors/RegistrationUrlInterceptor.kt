package com.example.saurabh.flckr.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class RegistrationUrlInterceptor @Inject constructor() : Interceptor {
    private var host: String? = null

    fun setHost(host: String?) {
        this.host = host
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (request.header("RegistrationApi") == "true" && !host.isNullOrBlank()) {
            val newUrl = request.url().newBuilder().host(host!!).build()
            request = request.newBuilder().url(newUrl).build()
        }
        return chain.proceed(request)
    }
}