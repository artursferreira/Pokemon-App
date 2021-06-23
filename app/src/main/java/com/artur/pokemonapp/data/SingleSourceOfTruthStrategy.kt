package com.artur.pokemonapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.artur.pokemonapp.data.Result.Status.ERROR
import com.artur.pokemonapp.data.Result.Status.SUCCESS
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [Result.Status.SUCCESS] - with data from database
 * [Result.Status.ERROR] - if error has occurred from any source
 * [Result.Status.LOADING]
 */
fun <T, A> resultLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> Result<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<Result<T>> =
        liveData(Dispatchers.IO) {
            emit(Result.loading<T>())
            val source = databaseQuery.invoke().map { Result.success(it) }
            emitSource(source)

            val responseStatus = networkCall.invoke()
            if (responseStatus.status == SUCCESS) {
                saveCallResult(responseStatus.data!!)
            } else if (responseStatus.status == ERROR) {
                emit(Result.error<T>(responseStatus.message!!))
                emitSource(source)
            }
        }

 suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
    try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) return Result.success(body)
        }
        return error(" ${response.code()} ${response.message()}")
    } catch (e: Exception) {
        return error(e.message ?: e.toString())
    }
}

private fun <T> error(message: String): Result<T> {
    Log.e("network error", message)
    return Result.error("Network call has failed for a following reason: $message")
}