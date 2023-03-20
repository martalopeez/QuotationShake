package mylophue.quotationshake.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mylophue.quotationshake.data.settings.SettingsDataSource
import mylophue.quotationshake.data.settings.SettingsRepository

@Module @InstallIn(SingletonComponent::class)
abstract class SettingsBinderModule {
    @Binds
    abstract fun bindSettingsDataSource(settingsDS: SettingsDataSource): SettingsDataSource
    @Binds
    abstract fun bindSettingsDataRepository(settingsRepository: SettingsRepository): SettingsRepository
}