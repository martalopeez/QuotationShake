package mylophue.quotationshake.data.favourites

import kotlinx.coroutines.flow.Flow
import mylophue.quotationshake.data.favourites.model.QuotationDto

interface FavouritesDataSource {
    suspend fun addQuotation(quotation: QuotationDto)
    suspend fun deleteQuotation(quotation: QuotationDto)
    fun getAllQuotations(): Flow<List<QuotationDto>>
    fun getQuotationById(id: String): Flow<QuotationDto?>
    fun deleteAllQuotations()
}