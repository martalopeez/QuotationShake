package mylophue.quotationshake.data.favourites

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import mylophue.quotationshake.data.favourites.model.QuotationDto

@Dao
interface FavouritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotation(quotation: QuotationDto)

    @Delete
    suspend fun deleteQuotation(quotation: QuotationDto)

    @Query("SELECT * FROM ${FavouritesContract.FavsTable.favouritesTable}")
    fun getAllQuotations(): Flow<List<QuotationDto>>

    @Query("SELECT * FROM ${FavouritesContract.FavsTable.favouritesTable} WHERE ${FavouritesContract.FavsTable.identifier} = :id")
    fun getQuotationById(id: String): Flow<QuotationDto?>

    @Query("DELETE FROM ${FavouritesContract.FavsTable.favouritesTable}")
    fun deleteAllQuotations()
}