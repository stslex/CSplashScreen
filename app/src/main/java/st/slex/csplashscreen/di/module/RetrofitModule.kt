package st.slex.csplashscreen.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import st.slex.csplashscreen.data.core.Constants.BASE_URL
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providesRetrofitClient(
        mLoggingInterceptor: HttpLoggingInterceptor,
        onlineInterceptor: Interceptor,
        application: Application
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(mLoggingInterceptor)
        .addInterceptor(onlineInterceptor)
        .cache(Cache(application.cacheDir, 10 * 1024 * 1024 * 8L))
        .build()

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun providesOnlineInterceptor(): Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60 * 60 * 3
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }

    /*   @OfflineScope
       @Provides
       fun providesOfflineInterceptor(): Interceptor = Interceptor { chain ->
           var request: Request = chain.request()
           val maxStale = 60 * 60 * 3
           request = request.newBuilder()
               .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
               .removeHeader("Pragma")
               .build()

           chain.proceed(request)
       }*/

}