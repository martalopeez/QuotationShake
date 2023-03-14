package mylophue.quotationshake.data.settings

import java.util.concurrent.Flow

interface SettingsDataSource {
    fun getUsername(): Flow<String> {}
}