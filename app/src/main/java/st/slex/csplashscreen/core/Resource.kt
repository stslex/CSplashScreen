package st.slex.csplashscreen.core

sealed interface Resource<out T> {

    fun <U> map(mapper: Mapper.ToUI<in T, U>): U

    class Success<T>(val data: T) : Resource<T> {
        override fun <U> map(mapper: Mapper.ToUI<in T, U>): U = mapper.map(data)
    }

    class Failure(val exception: Exception) : Resource<Nothing> {
        override fun <U> map(mapper: Mapper.ToUI<in Nothing, U>): U = mapper.map(exception)
    }

    object Loading : Resource<Nothing> {
        override fun <U> map(mapper: Mapper.ToUI<in Nothing, U>): U = mapper.map()
    }
}