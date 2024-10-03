package com.githubrepo.domain.constants

import androidx.annotation.StringDef


/**
 * Keep all the network related constants here
 * Created by Lajesh Dineshkumar on 10/30/2019.
 * Company: Nagarro Middle East
 * Email: lajesh.dineshkumar@nagarro.com
 */
object NetworkConstants {
    @StringDef(
        NetworkErrorCodes.SERVICE_UNAVAILABLE,
        NetworkErrorCodes.MALFORMED_JSON,
        NetworkErrorCodes.NO_INTERNET,
        NetworkErrorCodes.UNEXPECTED_ERROR,
        NetworkErrorCodes.SESSION_TIMEOUT,
        NetworkErrorCodes.HTML_RESPONSE_ERROR
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class NetworkErrorCodes {
        companion object {
            const val SERVICE_UNAVAILABLE = "SERVICE_UNAVAILABLE"
            const val MALFORMED_JSON = "MALFORMED_JSON"
            const val NO_INTERNET = "NO_INTERNET"
            const val UNEXPECTED_ERROR = "UNEXPECTED_ERROR"
            const val SESSION_TIMEOUT = "SESSION_TIMEOUT"
            const val HTML_RESPONSE_ERROR = "HTML_RESPONSE_ERROR"
        }
    }

    @StringDef(
        NetworkErrorMessages.SERVICE_UNAVAILABLE, NetworkErrorMessages.MALFORMED_JSON,
        NetworkErrorMessages.NO_INTERNET, NetworkErrorMessages.UNEXPECTED_ERROR
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class NetworkErrorMessages {
        companion object {
            const val SERVICE_UNAVAILABLE = "System temporarily unavailable, please try again later"
            const val MALFORMED_JSON = "Oops! We hit an error. Try again later."
            const val NO_INTERNET = "Oh! You are not connected to a wifi or cellular data network. Please connect and try again"
            const val UNEXPECTED_ERROR = "Something went wrong"
            const val SESSION_TIMEOUT = "Your session has timed out. Please login again."
        }
    }
}