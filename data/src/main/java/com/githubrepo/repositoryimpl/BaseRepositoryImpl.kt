package com.githubrepo.repositoryimpl

import android.util.Log
import android.util.MalformedJsonException
import com.githubrepo.domain.common.NetworkResult
import com.githubrepo.domain.constants.NetworkConstants
import com.githubrepo.domain.entity.ErrorEntity
import kotlinx.coroutines.delay
import java.io.IOException
import java.net.SocketTimeoutException

open class BaseRepositoryImpl {

    protected suspend fun <T : Any> apiCall(call: suspend () -> T): NetworkResult<T> {
        return try {
            val response = call()
            Log.i("Question","onEventChange quizz response:"+response)
            NetworkResult.Success(response)
        } catch (ex: Throwable) {
            Log.i("Question","onEventChange quizz ex:"+ex)
            NetworkResult.Error(handleError(ex))
        }
    }

    protected suspend fun <T : Any> apiCallRetry(
        times: Int = 3,
        call: suspend () -> T
    ): NetworkResult<T> {
        return try {
            val response = apiCallIO(timesValue = times, block = call)
            NetworkResult.Success(response)
        } catch (ex: Throwable) {
            NetworkResult.Error(handleError(ex))
        }
    }

    private suspend fun <T> apiCallIO(
        timesValue: Int = 4,
        initialDelay: Long = 100, // 0.1 second
        maxDelay: Long = 1000,    // 1 second
        factor: Double = 2.0,
        block: suspend () -> T
    ): T {
        var currentDelay = initialDelay
        repeat(timesValue - 1) {
            try {
                return block()
            } catch (e: IOException) {
                // you can log an error here and/or make a more finer-grained
                // analysis of the cause to see if retry is needed
            }
            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
        }
        return block() // last attempt
    }
     /**
      * This method returns the proper error codes and error messages for various
      * network exceptions and API related errors
      */
     private fun handleError(throwable: Throwable): ErrorEntity.Error {
         return when (throwable) {

             is SocketTimeoutException -> {
                 ErrorEntity.Error(
                     NetworkConstants.NetworkErrorCodes.SERVICE_UNAVAILABLE,
                     NetworkConstants.NetworkErrorMessages.SERVICE_UNAVAILABLE
                 )
             }

             is MalformedJsonException -> {
                 ErrorEntity.Error(
                     NetworkConstants.NetworkErrorCodes.MALFORMED_JSON,
                     NetworkConstants.NetworkErrorMessages.MALFORMED_JSON
                 )
             }

             is IOException -> {
                 ErrorEntity.Error(
                     NetworkConstants.NetworkErrorCodes.NO_INTERNET,
                     NetworkConstants.NetworkErrorMessages.NO_INTERNET
                 )
             }

           /*  is HttpException -> {
                     ErrorEntity.Error(
                         NetworkConstants.NetworkErrorCodes.UNEXPECTED_ERROR,
                         NetworkConstants.NetworkErrorMessages.UNEXPECTED_ERROR
                     )
             }*/
             else -> { //can be InterruptedIOException or any other
                 ErrorEntity.Error(
                     NetworkConstants.NetworkErrorCodes.UNEXPECTED_ERROR,
                     NetworkConstants.NetworkErrorMessages.UNEXPECTED_ERROR
                 )
             }
         }
     }

 }