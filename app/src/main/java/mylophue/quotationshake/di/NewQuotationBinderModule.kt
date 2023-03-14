package mylophue.quotationshake.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mylophue.quotationshake.data.newquotation.NewQuotationDataSource
import mylophue.quotationshake.data.newquotation.NewQuotationDataSourceImpl
import mylophue.quotationshake.data.newquotation.NewQuotationRepository
import mylophue.quotationshake.data.newquotation.NewQuotationRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NewQuotationBinderModule {
    @Binds
    abstract fun bindNewQuotationRepository(
        repository: NewQuotationRepositoryImpl
    ): NewQuotationRepository

    @Binds
    abstract fun bindNewQuotationDataSource(dataSource: NewQuotationDataSourceImpl): NewQuotationDataSource
}