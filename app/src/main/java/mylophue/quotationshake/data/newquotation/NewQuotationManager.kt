package mylophue.quotationshake.data.newquotation

import mylophue.quotationshake.ui.model.Quotation

interface NewQuotationManager {
    suspend fun getNewQuotation(): Result<Quotation>
}