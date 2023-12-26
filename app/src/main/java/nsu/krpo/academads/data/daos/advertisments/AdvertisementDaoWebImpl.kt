package nsu.krpo.academads.data.daos.advertisments

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.data.network.AcademAdsAPIService
import nsu.krpo.academads.data.network.mappers.AdvertisementToDomainMapper
import nsu.krpo.academads.data.network.mappers.CategoriesToDomainMapper
import nsu.krpo.academads.data.network.mappers.UserToDomainMapper
import nsu.krpo.academads.data.network.models.AdvertisementCreate
import nsu.krpo.academads.data.network.models.AdvertisementResponse
import nsu.krpo.academads.data.network.models.AdvertisementUpdate
import nsu.krpo.academads.data.network.models.BookingRequest
import nsu.krpo.academads.data.network.models.FavoriteAdvertisementsRequest
import nsu.krpo.academads.data.network.models.FavoriteUserRequest
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementPhoto
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.domain.model.ads.User
import java.math.BigDecimal
import java.util.Date
import javax.inject.Inject

class AdvertisementsDaoWebImpl @Inject constructor(
    private val service: AcademAdsAPIService,
) : AdvertisementsDao {


    override fun getAll(): Single<List<Advertisement>> {
        val ads = service.getAdvertisements()
        val list: List<AdvertisementResponse>? = ads.execute().body()
        val mapper = AdvertisementToDomainMapper()
        val categoryMapper = CategoriesToDomainMapper()
        val userMapper = UserToDomainMapper()
        return Single.just(list!!.map {
            val userResponse = service.getUserById(it.author).blockingGet()
            val user = userMapper.fromResponse(
                userResponse,
                service.getAvatarById(userResponse.avatar).blockingGet()
            )
            val photos = service.getPhotoById(it.id)
            val adsPhotos = photos.map {
                AdvertisementPhoto(
                    BitmapDrawable(
                        null,
                        BitmapFactory.decodeByteArray(it.image, 0, it.image.size)
                    )
                )
            }
            mapper.fromResponse(
                it,
                categoryMapper.fromName(it.category),
                user,
                listOf(adsPhotos.blockingGet())
            )
        })
    }

    override fun getAllById(userId: Long): Single<List<Advertisement>> {
        val mapper = AdvertisementToDomainMapper()
        val categoryMapper = CategoriesToDomainMapper()
        val userMapper = UserToDomainMapper()
        val userResponse = service.getUserById(userId).blockingGet()
        val user = userMapper.fromResponse(
            userResponse,
            service.getAvatarById(userResponse.avatar).blockingGet()
        )
        val ads = service.getAdvertisements()
        val list: List<AdvertisementResponse>? =
            ads.execute().body()!!.filter { it.author == user.id }
        return Single.just(list!!.map {
            val photos = service.getPhotoById(it.id)
            val adsPhotos = photos.map {
                AdvertisementPhoto(
                    BitmapDrawable(
                        null,
                        BitmapFactory.decodeByteArray(it.image, 0, it.image.size)
                    )
                )
            }
            mapper.fromResponse(
                it,
                categoryMapper.fromName(it.category),
                user,
                listOf(adsPhotos.blockingGet())
            )
        })
    }

    override fun createAd(
        header: String,
        description: String,
        price: BigDecimal,
        category: Category,
        authorId: Long
    ): Completable {
        val createAd = AdvertisementCreate(header, description, price, category.name, authorId)
        service.createAdvertisement(createAd)
        return Completable.complete()
    }

    override fun editAd(
        id: Long,
        header: String,
        description: String,
        price: BigDecimal,
        category: Category,
        status: AdvertisementStatus
    ): Completable {
        val updateAd =
            AdvertisementUpdate(id, header, description, price, category.name, status.name)
        service.updateAdvertisement(id, updateAd)
        return Completable.complete()
    }

    override fun getAllByCategory(category: Category): Single<List<Advertisement>> = Single.fromCallable { getAllByCategoryBlocking(category) }

    private fun getAllByCategoryBlocking(category: Category) : List<Advertisement> {
        val mapper = AdvertisementToDomainMapper()
        val categoryMapper = CategoriesToDomainMapper()
        val userMapper = UserToDomainMapper()
        val ads = service.getAdvertisements()
        val list: List<AdvertisementResponse>? =
            ads.execute().body()!!.filter { it.category == category.name }
        return list!!.map {
            val userResponse = service.getUserById(it.author).blockingGet()
            val user = userMapper.fromResponse(
                userResponse,
                service.getAvatarById(userResponse.avatar).blockingGet()
            )
            val photos = service.getPhotoById(it.id)
            val adsPhotos = photos.map {
                AdvertisementPhoto(
                    BitmapDrawable(
                        null,
                        BitmapFactory.decodeByteArray(it.image, 0, it.image.size)
                    )
                )
            }
            mapper.fromResponse(
                it,
                categoryMapper.fromName(it.category),
                user,
                listOf(adsPhotos.blockingGet())
            )
        }
    }

    override fun changeAdStatus(ad: Advertisement, status: AdvertisementStatus): Completable {
        return editAd(ad.id, ad.header, ad.description, ad.price, ad.category, status)
    }

    override fun book(ad: Advertisement, userId: Long, until: Date): Completable {
        val bookingRequest = BookingRequest(ad.id, ad.price, until.toString())
        service.createBooking(bookingRequest)
        return Completable.complete()
    }

    override fun like(ad: Advertisement, userId: Long): Completable {
        val request = FavoriteAdvertisementsRequest(ad.id, userId)
        service.addToFavoriteAdvertisements(request)
        return Completable.complete()
    }

    override fun subscribe(user: User, subscriberId: Long): Completable {
        val request = FavoriteUserRequest(user.id, subscriberId)
        service.addToFavoriteUsers(request)
        return Completable.complete()
    }
}