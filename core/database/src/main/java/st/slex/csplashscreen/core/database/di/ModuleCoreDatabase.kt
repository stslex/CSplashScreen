package st.slex.csplashscreen.core.database.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import st.slex.csplashscreen.core.database.AppDatabase
import st.slex.csplashscreen.core.database.favourite.FavouriteDao
import st.slex.csplashscreen.core.database.search.SearchDao

val moduleCoreDatabase = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            AppDatabase.NAME
        )
            .build()
    }

    single<SearchDao> { get<AppDatabase>().searchDao }
    single<FavouriteDao> { get<AppDatabase>().favouriteDao }
}