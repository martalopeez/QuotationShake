package mylophue.quotationshake.data.settings

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(private val sharedPref: SharedPreferences): SettingsDataSource {
    object Preferences {
        const val editPreference = "editTextPreference"
        const val listPreference = "listPreference"
    }
    override fun getUsername(): Flow<String> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            launch(Dispatchers.IO) {
                if (Preferences.editPreference == key) {
                    trySend(getUsernamePreference())
                }
            }
        }
        sharedPref.registerOnSharedPreferenceChangeListener(listener)
        trySend(getUsernamePreference())
        awaitClose { sharedPref.unregisterOnSharedPreferenceChangeListener(listener) }
    }.flowOn(Dispatchers.IO)

    override fun getLanguage(): Flow<String> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            launch(Dispatchers.IO) {
                if (Preferences.listPreference == key) {
                    trySend(getLanguagePreferences())
                }
            }
        }
        sharedPref.registerOnSharedPreferenceChangeListener(listener)
        trySend(getLanguagePreferences())
        awaitClose {
            sharedPref.unregisterOnSharedPreferenceChangeListener(listener) }
    }.flowOn(Dispatchers.IO)

    private fun getUsernamePreference() = sharedPref.getString(Preferences.editPreference, "") ?: ""
    private fun getLanguagePreferences() = sharedPref.getString(Preferences.listPreference, "") ?: ""
}