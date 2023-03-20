package mylophue.quotationshake.ui.newquotation

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mylophue.quotationshake.data.newquotation.NewQuotationManager
import mylophue.quotationshake.data.newquotation.NewQuotationRepository
import mylophue.quotationshake.data.settings.SettingsRepository
import mylophue.quotationshake.ui.model.Quotation
import javax.inject.Inject

@HiltViewModel
class NewQuotationViewModel @Inject constructor(private val manager: NewQuotationManager, settingsRepository: SettingsRepository): ViewModel() {

    val userName: LiveData<String> = settingsRepository.getUsername().asLiveData()

    private val _quotation = MutableLiveData<Quotation>()
    val quotation: LiveData<Quotation>
        get() = _quotation

    private val _isRefreshing = MutableLiveData<Boolean>(false)
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    val isGreetingsVisible = Transformations.map(quotation) { it == null }

    private val _favIsVisible = MutableLiveData<Boolean>(false)
    val favIsVisible: LiveData<Boolean>
        get() = _favIsVisible

    private val _existsError = MutableLiveData<Throwable?>(null)
    val existsError: LiveData<Throwable?> get() = _existsError

    fun getNewQuotation() {
        _isRefreshing.value = true
        viewModelScope.launch {
            manager.getNewQuotation().fold(onSuccess = { _quotation.value = it }, onFailure = { _existsError.value = it })
        }
        _isRefreshing.value = false
        _favIsVisible.value = true
    }

    fun addToFavourites() {
        _favIsVisible.value = false
    }

    fun resetError() {
        _existsError.value = null
    }
}