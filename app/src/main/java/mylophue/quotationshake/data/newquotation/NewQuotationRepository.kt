package mylophue.quotationshake.data.newquotation

import mylophue.quotationshake.ui.model.Quotation

interface NewQuotationRepository {
    suspend fun getNewQuotation(): Result<Quotation>
}