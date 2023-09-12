package st.slex.csplashscreen.core.favourite.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import st.slex.csplashscreen.core.favourite.data.datasource.FavouriteDao
import st.slex.csplashscreen.core.favourite.data.datasource.FavouriteDatabase
import javax.inject.Singleton

@Module
class FavouriteDatabaseModule {

    @Provides
    @Singleton
    fun provideDao(context: Context): FavouriteDao = Room
        .databaseBuilder(
            context,
            FavouriteDatabase::class.java,
            FavouriteDatabase.NAME
        )
        .build()
        .dao
}