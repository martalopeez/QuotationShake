package mylophue.quotationshake.ui.newquotation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import mylophue.quotationshake.ui.model.Quotation

class NewQuotationViewModel: ViewModel() {
    private val _userName: MutableLiveData<String> = getUserName()
    val userName: LiveData<String> get() = _userName

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

    private fun getUserName(): MutableLiveData<String> {
        return MutableLiveData(setOf("Alice", "Bob", "Charlie", "David", "Emma").random());
    }

    fun getNewQuotation() {
        _isRefreshing.value = true
        val num = (0..99).random().toString()
        _quotation.value = Quotation(num, "Quotation text #$num", "Author #$num")
        _isRefreshing.value = false
        _favIsVisible.value = true
    }

    fun addToFavourites() {
        _favIsVisible.value = false
    }
}