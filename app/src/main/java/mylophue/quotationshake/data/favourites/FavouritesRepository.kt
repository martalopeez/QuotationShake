package mylophue.quotationshake.data.favourites

import kotlinx.coroutines.flow.Flow
import mylophue.quotationshake.ui.model.Quotation

interface FavouritesRepository {
    suspend fun addQuotation(quotation: Quotation)
    suspend fun deleteQuotation(quotation: Quotation)
    fun getAllQuotations(): Flow<List<Quotation>>
    fun getQuotationById(id: String): Flow<Quotation?>
    fun deleteAllQuotations()
}