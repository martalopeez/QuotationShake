package mylophue.quotationshake.data.settings

import android.content.SharedPreferences
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(private val sharedPref: SharedPreferences): SettingsDataSource {
    val preference = "editTextPreference"
}