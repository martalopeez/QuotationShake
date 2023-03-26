package mylophue.quotationshake.data.newquotation

import mylophue.quotationshake.ui.model.Quotation
import mylophue.quotationshake.utils.NoInternetException
import mylophue.quotationshake.data.newquotation.model.toDomain
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(
    private val dataSource: NewQuotationDataSource,
    private val connectivityChecker: ConnectivityChecker
) : NewQuotationRepository {
    override suspend fun getNewQuotation(language: String): Result<Quotation> {
        return if (connectivityChecker.isConnectionAvailable()) dataSource.getQuotation(language).toDomain()
        else Result.failure(NoInternetException())
    }
}