package com.example.marvelhub.utils


data class DataState<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): DataState<T> {
            return DataState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): DataState<T> {
            return DataState(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): DataState<T> {
            return DataState(Status.LOADING, data, null)
        }

    }

}