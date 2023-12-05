package nsu.krpo.academads.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nsu.krpo.academads.data.credentials.StubCredantialsStorageImpl
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDaoStubImpl
import nsu.krpo.academads.data.daos.categories.CategoriesDao
import nsu.krpo.academads.data.daos.categories.CategoriesDaoStubImpl
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.data.daos.saved_info.SavedRepImpl
import nsu.krpo.academads.data.daos.users.UsersDao
import nsu.krpo.academads.data.daos.users.UsersDaoStubImpl
import nsu.krpo.academads.domain.repository.CredentialsStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindCredentialsStorage(impl: StubCredantialsStorageImpl): CredentialsStorage

    @Binds
    abstract fun bindCategoriesDao(impl: CategoriesDaoStubImpl): CategoriesDao

    @Binds
    abstract fun bindAdvertismentsDao(impl: AdvertisementsDaoStubImpl): AdvertisementsDao

    @Binds
    abstract fun bindUserDao(impl: UsersDaoStubImpl): UsersDao

    companion object {
        @Singleton
        @Provides
        fun provideSavedRepository(@ApplicationContext context: Context): SavedRep = SavedRepImpl(context)

    }
}