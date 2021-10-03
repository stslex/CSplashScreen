package st.slex.csplashscreen.data.core

interface Mapper {

    interface Data<S, R> : Mapper {
        fun map(data: S): R
    }

    interface DataToUI<S, R> : Data<S, R> {
        fun map(exception: Exception): R
    }

    class Empty : Mapper
}