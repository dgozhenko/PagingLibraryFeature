package com.example.paginglibraryfeature.util

import java.io.IOException
import java.lang.Exception

suspend fun <T: Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> = try {
    call.invoke()
} catch (exception: Exception) {
    Result.Error(IOException(errorMessage, exception))
}
val <T> T.exhaustive: T get() = this