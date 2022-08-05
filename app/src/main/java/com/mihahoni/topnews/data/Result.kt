package com.mihahoni.topnews.data

import java.lang.Exception

sealed class Result<out R> {


    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception, val errorCode: Int? = null) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$errorCode]"
            Loading -> "Loading"
        }
    }
}

val Result<*>.succeeded
    get() = this is Result.Success && data != null