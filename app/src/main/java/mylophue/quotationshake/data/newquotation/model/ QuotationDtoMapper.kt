package mylophue.quotationshake.data.newquotation.model

import mylophue.quotationshake.ui.model.Quotation
import retrofit2.Response
import java.io.IOException

fun QuotationDto.toDomain() = Quotation(id = quoteLink, quotation = quoteText, author = quoteAuthor)

fun Response<QuotationDto>.toDomain() =
    if (isSuccessful) Result.success((body() as QuotationDto).toDomain())
    else Result.failure(IOException())