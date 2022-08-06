package com.mihahoni.topnews.utils

sealed class StateHandler {
    class Success<T>(var data: T?) : StateHandler()
    class Failure(var message: String?) : StateHandler()
    object Loading : StateHandler()
}