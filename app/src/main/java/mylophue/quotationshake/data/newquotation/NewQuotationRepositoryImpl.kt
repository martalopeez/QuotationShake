package mylophue.quotationshake.data.newquotation

import mylophue.quotationshake.ui.model.Quotation
import mylophue.quotationshake.utils.NoInternetException
import toDomain
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(
    private val dataSource: NewQuotationDataSource,
    private val connectivityChecker: ConnectivityChecker
) : NewQuotationRepository {
    override suspend fun getNewQuotation(): Result<Quotation> {
        return if (connectivityChecker.isConnectionAvailable()) dataSource.getQuotation(
            arrayOf(
                "en",
                "ru",
                "xx"
            ).random()
        ).toDomain()
        else Result.failure(NoInternetException())
    }
}