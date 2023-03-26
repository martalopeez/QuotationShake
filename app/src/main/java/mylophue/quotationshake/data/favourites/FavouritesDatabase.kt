package mylophue.quotationshake.data.favourites

import androidx.room.Database
import androidx.room.RoomDatabase
import mylophue.quotationshake.data.favourites.model.QuotationDto

@Database(entities = [QuotationDto::class], version = 1)
abstract class FavouritesDatabase: RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
}