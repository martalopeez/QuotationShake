package mylophue.quotationshake.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mylophue.quotationshake.data.settings.SettingsDataSource
import mylophue.quotationshake.data.settings.SettingsDataSourceImpl
import mylophue.quotationshake.data.settings.SettingsRepository
import mylophue.quotationshake.data.settings.SettingsRepositoryImpl

@Module @InstallIn(SingletonComponent::class)
abstract class SettingsBinderModule {
    @Binds
    abstract fun bindSettingsDataSource(settingsDS: SettingsDataSourceImpl): SettingsDataSource
    @Binds
    abstract fun bindSettingsDataRepository(settingsRepository: SettingsRepositoryImpl): SettingsRepository
}