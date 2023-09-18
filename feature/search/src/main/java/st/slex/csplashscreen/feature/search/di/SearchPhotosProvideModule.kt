package st.slex.csplashscreen.feature.search.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import st.slex.csplashscreen.feature.search.data.database.SearchDao
import st.slex.csplashscreen.feature.search.data.database.SearchDatabase

@Module
class SearchPhotosProvideModule {

    @Provides
    @SearchPhotosScope
    fun providesDao(
        context: Context
    ): SearchDao = Room.databaseBuilder(
        context,
        SearchDatabase::class.java,
        SearchDatabase.NAME
    )
        .build()
        .dao
}