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
    val preference = "editTextPreference"
    val listPreference = "listPreference"
    override fun getUsername(): Flow<String> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            launch(Dispatchers.IO) {
                if (preference == key) {
                    trySend(getUsernamePreference())
                }
            }
        }
        sharedPref.registerOnSharedPreferenceChangeListener(listener)
        trySend(getUsernamePreference())
        awaitClose {
            sharedPref.unregisterOnSharedPreferenceChangeListener(listener) }
    }.flowOn(Dispatchers.IO)

    override fun getLanguage(): Flow<String> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            launch(Dispatchers.IO) {
                if (listPreference == key) {
                    trySend(getUsernamePreference())
                }
            }
        }
        sharedPref.registerOnSharedPreferenceChangeListener(listener)
        trySend(getUsernamePreference())
        awaitClose {
            sharedPref.unregisterOnSharedPreferenceChangeListener(listener) }
    }.flowOn(Dispatchers.IO)


    private fun getUsernamePreference() = sharedPref.getString(preference, "") ?: ""
}