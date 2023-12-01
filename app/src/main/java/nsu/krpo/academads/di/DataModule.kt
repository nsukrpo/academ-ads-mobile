package nsu.krpo.academads.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nsu.krpo.academads.data.credentials.StubCredantialsStorageImpl
import nsu.krpo.academads.data.daos.advertisments.AdvertismentsDao
import nsu.krpo.academads.data.daos.advertisments.AdvertismentsDaoImpl
import nsu.krpo.academads.data.daos.categories.CategoriesDao
import nsu.krpo.academads.data.daos.categories.CategoriesDaoImpl
import nsu.krpo.academads.domain.repository.CredentialsStorage

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindCredentialsStorage(impl: StubCredantialsStorageImpl): CredentialsStorage

    @Binds
    abstract fun bindCategoriesDao(impl: CategoriesDaoImpl): CategoriesDao

    @Binds
    abstract fun bindAdvertismentsDao(impl: AdvertismentsDaoImpl): AdvertismentsDao
}