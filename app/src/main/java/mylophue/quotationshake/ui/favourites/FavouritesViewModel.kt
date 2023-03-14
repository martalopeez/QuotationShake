package mylophue.quotationshake.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import mylophue.quotationshake.ui.model.Quotation
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(): ViewModel() {
    private var _favQuotations = MutableLiveData<List<Quotation>>(getfavoriteQuotations())
    val favQuotations: LiveData<List<Quotation>> get() = _favQuotations

    val isDeleteAllVisible = Transformations.map(favQuotations) { it.isNotEmpty() }

    private fun getfavoriteQuotations(): List<Quotation> {
        val listFavs: List<Quotation> = (0..20).map {
            val num = (0..99).random().toString()
            Quotation(num, "Quotation text #$num", "Author #$num")
        }
        listFavs.toMutableList().add(Quotation("23", "Dos cosas son infinitas: la estupidez humana y el universo; y no estoy seguro de lo segundo.", "Albert Einstein"))
        listFavs.toMutableList().add(Quotation("24", "Me gustan los catalanes. Hacen cosas.", "Anonymous"))
        return listFavs;
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