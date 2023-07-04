package com.stslex.csplashscreen.core

interface Mapper {

    interface Data<S, R> : Mapper {
        fun map(data: S): R
    }

    interface ToUI<S, R> : Data<S, R> {
        fun map(exception: Exception): R
        fun map(): R
    }
}