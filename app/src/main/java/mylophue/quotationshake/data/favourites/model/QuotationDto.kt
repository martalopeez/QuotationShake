package mylophue.quotationshake.data.favourites.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mylophue.quotationshake.data.favourites.FavouritesContract

@Entity (FavouritesContract.FavsTable.favouritesTable)
data class QuotationDto(@PrimaryKey @ColumnInfo(FavouritesContract.FavsTable.identifier) val identifier: String, @ColumnInfo(FavouritesContract.FavsTable.quote) val quotation: String, @ColumnInfo(FavouritesContract.FavsTable.author) val author: String)
