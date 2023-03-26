package mylophue.quotationshake.ui.favourites

import androidx.lifecycle.*
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mylophue.quotationshake.data.favourites.FavouritesRepository
import mylophue.quotationshake.ui.model.Quotation
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val favouritesRepository: FavouritesRepository): ViewModel() {
    val favQuotations = favouritesRepository.getAllQuotations().asLiveData()
    val isDeleteAllVisible = favQuotations.map { it.isNotEmpty() }

    fun deleteAllQuotations() {
        viewModelScope.launch {
            favouritesRepository.deleteAllQuotations()
        }
    }

    fun deleteQuotationAtPosition(position: Int) {
        viewModelScope.launch {
            favouritesRepository.deleteQuotation(favQuotations.value!![position])
        }
    }
}