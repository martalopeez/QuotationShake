package mylophue.quotationshake.data.favourites

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mylophue.quotationshake.data.favourites.model.QuotationDto
import mylophue.quotationshake.data.favourites.model.toDomain
import mylophue.quotationshake.data.favourites.model.toDto
import mylophue.quotationshake.ui.model.Quotation
import javax.inject.Inject

class FavouritesRespositoryImpl @Inject constructor(private val favouritesDataSource: FavouritesDataSource): FavouritesRepository {
    override suspend fun addQuotation(quotation: Quotation) {
        favouritesDataSource.addQuotation(quotation.toDto())
    }

    override suspend fun deleteQuotation(quotation: Quotation) {
        favouritesDataSource.deleteQuotation(quotation.toDto())
    }

    override fun getAllQuotations(): Flow<List<Quotation>> {
        return favouritesDataSource.getAllQuotations().map {
                list -> list.map { it.toDomain() }
        }
    }

    override fun getQuotationById(id: String): Flow<Quotation?> {
        return favouritesDataSource.getQuotationById(id).map {
            it?.toDomain()
        }
    }

    override fun deleteAllQuotations() {
        favouritesDataSource.deleteAllQuotations()
    }
}