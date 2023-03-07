package mylophue.quotationshake.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import mylophue.quotationshake.ui.model.Quotation

class FavouritesViewModel: ViewModel() {
    private var _favQuotations = MutableLiveData<List<Quotation>>(getfavoriteQuotations())
    val favQuotations: LiveData<List<Quotation>> get() = _favQuotations

    val isDeleteAllVisible = Transformations.map(favQuotations) { it.isNotEmpty() }

    private fun getfavoriteQuotations(): List<Quotation> {
        return (0..20).map {
            val num = (0..99).random().toString()
            Quotation(num, "Quotation text #$num", "Author #$num")
        }
    }

    fun deleteAllQuotations() {
        _favQuotations.value = emptyList()
    }

    fun deleteQuotationAtPosition(position: Int) {
        val list = _favQuotations.value?.toMutableList()
        list?.removeAt(position)
        _favQuotations.value = list ?: emptyList()
    }
}