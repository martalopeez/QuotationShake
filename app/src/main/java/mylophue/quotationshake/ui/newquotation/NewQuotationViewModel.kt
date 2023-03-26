package mylophue.quotationshake.ui.newquotation

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mylophue.quotationshake.data.favourites.FavouritesRepository
import mylophue.quotationshake.data.newquotation.NewQuotationManager
import mylophue.quotationshake.data.settings.SettingsRepository
import mylophue.quotationshake.ui.model.Quotation
import javax.inject.Inject

@HiltViewModel
class NewQuotationViewModel @Inject constructor(private val SettingsRepository: SettingsRepository, private val manager: NewQuotationManager, private val favouritesRepository: FavouritesRepository): ViewModel() {
    val userName: LiveData<String> = SettingsRepository.getUsername().asLiveData()

    private val _quotation = MutableLiveData<Quotation>()
    val quotation: LiveData<Quotation>
        get() = _quotation

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    val isGreetingsVisible = quotation.map { it.id.isEmpty() }

    val favIsVisible = quotation.switchMap { newQuotation ->
        favouritesRepository.getQuotationById(newQuotation.id).asLiveData()
    }.map { favourite -> favourite == null }

    private val _existsError = MutableLiveData<Throwable?>(null)
    val existsError: LiveData<Throwable?> get() = _existsError

    fun getNewQuotation() {
        _isRefreshing.value = true
        //favouritesRepository.getAllQuotations()
        viewModelScope.launch {
            manager.getNewQuotation().fold(onSuccess = { _quotation.value = it }, onFailure = { _existsError.value = it })
        }
        _isRefreshing.value = false
    }

    fun addToFavourites() {
        viewModelScope.launch{
            favouritesRepository.addQuotation(quotation.value!!)
        }
    }

    fun resetError() {
        _existsError.value = null
    }
}