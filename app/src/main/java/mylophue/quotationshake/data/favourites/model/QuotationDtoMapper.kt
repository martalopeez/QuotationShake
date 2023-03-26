package mylophue.quotationshake.data.favourites.model
import mylophue.quotationshake.ui.model.Quotation

fun QuotationDto.toDomain() = Quotation(id = identifier, quotation = quotation, author = author)
fun Quotation.toDto() = QuotationDto(identifier = id, quotation = quotation, author = author)