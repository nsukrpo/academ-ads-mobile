package nsu.krpo.academads.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nsu.krpo.academads.data.StubCredantialsStorageImpl
import nsu.krpo.academads.domain.repository.CredentialsStorage

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindCredentialsStorage(impl: StubCredantialsStorageImpl): CredentialsStorage
}