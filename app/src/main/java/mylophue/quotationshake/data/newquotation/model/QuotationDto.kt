package mylophue.quotationshake.data.newquotation.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuotationDto(val quoteText: String, val quoteAuthor: String, val senderName: String, val senderLink: String, val quoteLink: String)