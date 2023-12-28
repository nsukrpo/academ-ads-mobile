package nsu.krpo.academads.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nsu.krpo.academads.data.credentials.StubCredentialsStorageImpl
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDao
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDaoStubImpl
import nsu.krpo.academads.data.daos.advertisments.AdvertisementsDaoWebImpl
import nsu.krpo.academads.data.daos.authorization.AuthDao
import nsu.krpo.academads.data.daos.authorization.AuthDaoImpl
import nsu.krpo.academads.data.daos.authorization.AuthDaoStubImpl
import nsu.krpo.academads.data.daos.bans.BansDao
import nsu.krpo.academads.data.daos.bans.BansDaoStubImpl
import nsu.krpo.academads.data.daos.categories.CategoriesDao
import nsu.krpo.academads.data.daos.categories.CategoriesDaoStubImpl
import nsu.krpo.academads.data.daos.likes.LikesDao
import nsu.krpo.academads.data.daos.likes.LikesDaoStubImpl
import nsu.krpo.academads.data.daos.likes.LikesDaoWebImpl
import nsu.krpo.academads.data.daos.purchases.PurchasesDao
import nsu.krpo.academads.data.daos.purchases.PurchasesDaoStubImpl
import nsu.krpo.academads.data.daos.purchases.PurchasesDaoWebImpl
import nsu.krpo.academads.data.daos.recommendations.RecommendationsDao
import nsu.krpo.academads.data.daos.recommendations.RecommendationsDaoStubImpl
import nsu.krpo.academads.data.daos.saved_info.SavedRep
import nsu.krpo.academads.data.daos.saved_info.SavedRepImpl
import nsu.krpo.academads.data.daos.users.UsersDao
import nsu.krpo.academads.data.daos.users.UsersDaoStubImpl
import nsu.krpo.academads.data.daos.users.UsersDaoWebImpl
import nsu.krpo.academads.data.network.AcademAdsAPIService
import nsu.krpo.academads.data.network.RetrofitInstance
import nsu.krpo.academads.domain.repository.CredentialsStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
   // @Binds
   // abstract fun bindCredentialsStorage(impl: StubCredentialsStorageImpl): CredentialsStorage

    @Binds
    abstract fun bindCategoriesDao(impl: CategoriesDaoStubImpl): CategoriesDao

    //@Binds
    //abstract fun bindAdvertisementsDao(impl: AdvertisementsDaoWebImpl): AdvertisementsDao

  //  @Binds
  //  abstract fun bindUserDao(impl: UsersDaoStubImpl): UsersDao

   // @Binds
    //abstract fun bindPurchasesDao(impl: PurchasesDaoStubImpl): PurchasesDao

    //@Binds
    //abstract fun bindLikesDao(impl: LikesDaoStubImpl): LikesDao

    @Binds
    abstract fun bindBansDao(impl: BansDaoStubImpl): BansDao

    @Binds
    abstract fun bindAuthDao(impl: AuthDaoStubImpl): AuthDao

    @Binds
    abstract fun bindRecommendationsDao(impl: RecommendationsDaoStubImpl): RecommendationsDao

    companion object {
        @Singleton
        @Provides
        fun provideSavedRepository(@ApplicationContext context: Context): SavedRep = SavedRepImpl(context)

        @Singleton
        @Provides
        fun provideWebApi(): AcademAdsAPIService {
            return RetrofitInstance().api
        }

        @Singleton
        @Provides
        fun provideCredStore(): CredentialsStorage {
            return StubCredentialsStorageImpl()
        }


        @Provides
        fun provideAdvertisementsDao(@ApplicationContext context: Context, service: AcademAdsAPIService): AdvertisementsDao {
            return AdvertisementsDaoWebImpl(context, service)
        }

        @Provides
        fun provideUsersDao(@ApplicationContext context: Context, service: AcademAdsAPIService): UsersDao {
            return UsersDaoWebImpl(context, service)
        }

        @Provides
        fun providePurchasesDao(@ApplicationContext context: Context, service: AcademAdsAPIService): PurchasesDao {
            return PurchasesDaoWebImpl(context, service)
        }

        @Provides
        fun provideLikesDao(@ApplicationContext context: Context, service: AcademAdsAPIService): LikesDao {
            return LikesDaoWebImpl(context, service)
        }

    }
}