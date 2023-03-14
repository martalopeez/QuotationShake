package mylophue.quotationshake.data.newquotation

import mylophue.quotationshake.data.newquotation.model.QuotationDto
import retrofit2.Response

interface NewQuotationDataSource {
    suspend fun getQuotation(language: String): Response<QuotationDto>
}