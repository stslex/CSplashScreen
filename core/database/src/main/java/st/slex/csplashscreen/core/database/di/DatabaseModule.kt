package st.slex.csplashscreen.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import st.slex.csplashscreen.core.database.AppDatabase
import st.slex.csplashscreen.core.database.favourite.FavouriteDao
import st.slex.csplashscreen.core.database.search.SearchDao
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.NAME
    )
        .build()

    @Provides
    @Singleton
    fun provideSearchDao(database: AppDatabase): SearchDao = database.searchDao

    @Provides
    @Singleton
    fun providesFavouriteDao(database: AppDatabase): FavouriteDao = database.favouriteDao
}