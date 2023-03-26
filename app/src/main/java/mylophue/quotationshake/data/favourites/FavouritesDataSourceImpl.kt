package mylophue.quotationshake.data.favourites

import kotlinx.coroutines.flow.Flow
import mylophue.quotationshake.data.favourites.model.QuotationDto
import mylophue.quotationshake.ui.model.Quotation
import javax.inject.Inject

class FavouritesDataSourceImpl @Inject constructor(private val favouritesDao: FavouritesDao): FavouritesDataSource {
    override suspend fun addQuotation(quotation: QuotationDto) {
        favouritesDao.addQuotation(quotation)
    }

    override suspend fun deleteQuotation(quotation: QuotationDto) {
        favouritesDao.deleteQuotation(quotation)
    }

    override fun getAllQuotations(): Flow<List<QuotationDto>> {
        return favouritesDao.getAllQuotations()
    }

    override fun getQuotationById(id: String): Flow<QuotationDto?> {
        return favouritesDao.getQuotationById(id)
    }

    override fun deleteAllQuotations() {
        favouritesDao.deleteAllQuotations()
    }
}