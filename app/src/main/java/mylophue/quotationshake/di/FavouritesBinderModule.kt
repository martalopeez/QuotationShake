package mylophue.quotationshake.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mylophue.quotationshake.data.favourites.FavouritesDataSource
import mylophue.quotationshake.data.favourites.FavouritesDataSourceImpl
import mylophue.quotationshake.data.favourites.FavouritesRepository
import mylophue.quotationshake.data.favourites.FavouritesRespositoryImpl

@Module @InstallIn(SingletonComponent::class)
abstract class FavouritesBinderModule {
    @Binds
    abstract fun bindFavouritesDataSource(favouritesDataSourceImpl: FavouritesDataSourceImpl): FavouritesDataSource
    @Binds
    abstract fun bindFavouritesRepository(favouritesRespositoryImpl: FavouritesRespositoryImpl): FavouritesRepository
}