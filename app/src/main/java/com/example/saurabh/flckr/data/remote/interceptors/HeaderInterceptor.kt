package com.example.saurabh.flckr.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class HeaderInterceptor @Inject
constructor() : Interceptor {
    private var registrationToken: String? = null

    fun setRegistrationToken(registrationToken: String?) {
        this.registrationToken = registrationToken
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestBuilder = request.newBuilder()
        return chain.proceed(requestBuilder.build())
    }
}