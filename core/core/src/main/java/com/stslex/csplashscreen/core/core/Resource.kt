package com.stslex.csplashscreen.core.core

import androidx.compose.runtime.Stable

@Stable
sealed interface Resource<out T> {

    fun <U> map(mapper: Mapper.ToUI<in T, U>): U

    @Stable
    data class Success<T>(val data: T) : Resource<T> {
        override fun <U> map(mapper: Mapper.ToUI<in T, U>): U = mapper.map(data)
    }

    @Stable
    data class Failure(val exception: Exception) : Resource<Nothing> {
        override fun <U> map(mapper: Mapper.ToUI<in Nothing, U>): U = mapper.map(exception)
    }

    @Stable
    object Loading : Resource<Nothing> {
        override fun <U> map(mapper: Mapper.ToUI<in Nothing, U>): U = mapper.map()
    }

    val dataIfSuccess: T?
        get() = (this as? Success)?.data
}