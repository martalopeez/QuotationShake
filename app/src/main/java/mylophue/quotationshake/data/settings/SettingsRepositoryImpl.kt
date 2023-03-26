package mylophue.quotationshake.data.settings

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val settingsDS: SettingsDataSource): SettingsRepository {
    override fun getUsername(): Flow<String> {
        return settingsDS.getUsername()
    }
    override fun getLanguage(): Flow<String> {
       return settingsDS.getLanguage()
    }
}