package st.slex.csplashscreen.data.core

sealed interface DataResult<D> {
    fun <U> map(mapper: Mapper.DataToUI<D, U>): U
    class Success<T>(val data: T) : DataResult<T> {
        override fun <U> map(mapper: Mapper.DataToUI<T, U>) = mapper.map(data)
    }

    class Failure<T>(val exception: Exception) : DataResult<T> {
        override fun <U> map(mapper: Mapper.DataToUI<T, U>): U =
            mapper.map(exception)
    }
}
