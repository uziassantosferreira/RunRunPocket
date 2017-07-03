package com.uzias.runrunpocket.core.networking.http

import okhttp3.Request

class HttpRequestBuilder(val request: Request) {

    companion object {
        val HTTP_HEADER_APP_KEY = "App-Key"
        val HTTP_HEADER_USER_TOKEN = "User-Token"
    }
    fun build(): Request {
        return request.newBuilder()
                .addHeader(HTTP_HEADER_APP_KEY, "")
                .addHeader(HTTP_HEADER_USER_TOKEN, "")
                .build()
    }
}