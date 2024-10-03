package com.githubrepo.config

object Constants {

    const val BASE_URL = "https://private-anon-ca3e4aafe0-githubtrendingapi.apiary-mock.com"
    const val BASE_URL_QUIZZ = "https://opentdb.com/api.php/"

    @Retention(AnnotationRetention.SOURCE)
    annotation class HEADER {
        companion object {
            const val CONTENT_TYPE = "Content-Type"
            const val DEVICE_ID = "deviceId"
            const val MOCK = "mock"
            const val APP_NAME = "appName"
            const val PACKAGE_NAME = "packageName"
            const val APP_VERSION = "appVersion"
            const val DEVICE_NAME = "deviceName"
            const val MODEL = "model"
            const val OS = "os"
            const val OS_VERSION = "osVersion"
            const val API_LEVEL = "apiLevel"
            const val APPLICATION_TYPE = "applicationType"
            const val ACCEPT_LANGUAGE = "Accept-Language"
            const val AUTHORIZATION = "Authorization"
            const val CHANNEL = "channel"
            const val USER_TOKEN = "userToken"
            const val USER_AGENT = "User-Agent"
            const val CORRELATION_ID = "Correlation-Id"
            const val REQUEST_TIME = "request_time"
            const val IBM_CLIENT_ID = "X-IBM-Client-Id"
            const val IBM_CLIENT_SECRET = "X-IBM-Client-Secret"
            const val HEADER_TOKEN = "token"
            const val ACCEPT = "accept"
            const val IPADDRESS = "ipAddress"
        }
    }

    @Retention(AnnotationRetention.SOURCE)
    annotation class ERRORCODES {
        companion object {
            const val SESSION_TIMEOUT = "SESSION_TIMEOUT"
            const val SESSION_EXPIRED = "SESSION_EXPIRED"
            const val SESSION_OVERWRITTEN = "SESSION_OVERWRITTEN"
            const val REDIRECT_TO_LOGIN = "REDIRECT_TO_LOGIN"
            const val INVALID_TOKEN = "invalid_token"
            const val UNAUTHORIZED = "unauthorized"
            const val APP_UPDATE = "ERR_UPGRADE_APP"
        }
    }

    @Retention(AnnotationRetention.SOURCE)
    annotation class OAuth {
        companion object {
            const val BEARER = "Bearer"
            const val REFRESH_TOKEN = "refresh_token"
            const val ACCESS_TOKEN = "12333"
        }
    }

}