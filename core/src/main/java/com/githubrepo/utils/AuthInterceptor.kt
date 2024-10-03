package com.githubrepo.utils

import com.githubrepo.config.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(private val accessToken: String) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = addCommonHeaders(originalRequest.newBuilder())
        return chain.proceed(requestBuilder.build())
    }

    private fun addCommonHeaders(newBuilder: Request.Builder): Request.Builder {
      return newBuilder.addHeader(Constants.HEADER.AUTHORIZATION,
          "${Constants.OAuth.BEARER} $accessToken")
                .addHeader(Constants.HEADER.ACCEPT,"application/json")

    }
}