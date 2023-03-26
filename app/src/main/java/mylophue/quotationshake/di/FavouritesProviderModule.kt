package mylophue.quotationshake.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mylophue.quotationshake.data.favourites.FavouritesContract
import mylophue.quotationshake.data.favourites.FavouritesDao
import mylophue.quotationshake.data.favourites.FavouritesDatabase
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
class FavouritesProviderModule {
    @Provides @Singleton
    fun provideFavouritesDatabase(@ApplicationContext context: Context): FavouritesDatabase {
        return Room.databaseBuilder(context, FavouritesDatabase::class.java, FavouritesContract.database).build()
    }
    @Provides
    fun provideFavouritesDao(favouritesDatabase: FavouritesDatabase): FavouritesDao {
        return favouritesDatabase.favouritesDao()
    }
}