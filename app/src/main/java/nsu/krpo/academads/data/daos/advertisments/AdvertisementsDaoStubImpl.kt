package nsu.krpo.academads.data.daos.advertisments

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.data.network.RetrofitInstance
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
import nsu.krpo.academads.domain.model.ads.UserType
import nsu.krpo.academads.domain.model.ads.UsersAvatar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import retrofit2.awaitResponse
import java.math.BigDecimal
import java.sql.Timestamp
import java.util.Date
import java.util.EnumSet
import javax.inject.Inject

class AdvertisementsDaoStubImpl @Inject constructor() : AdvertisementsDao {

    val user = User(
        1,
        "Evgeny",
        UsersAvatar(byteArrayOf(1, 123, 56, 89)),
        Date(18999),
        EnumSet.of(UserType.USER)
    )
    var service = RetrofitInstance()

    val ad = Advertisement(
        1,
        "Репетитор",
        "Важный, большой",
        BigDecimal(700),
        Category.EDUCATIONAL_SUPPLIES,
        user,
        Timestamp(220200),
        countWatch = 0,
        status = AdvertisementStatus.GRANTED,
        editDate = Timestamp(5656),
        photos = arrayListOf(
            AdvertisementPhoto(
                BitmapDrawable()
            )
        )
    )

    val ad3 = Advertisement(
        5,
        "Репетитор по матанализу",
        "Молодой",
        BigDecimal(400),
        Category.EDUCATIONAL_SUPPLIES,
        user,
        Timestamp(22028900),
        countWatch = 0,
        status = AdvertisementStatus.GRANTED,
        editDate = Timestamp(22028900),
        photos = arrayListOf(
            AdvertisementPhoto(
                BitmapDrawable()
            )
        )
    )


    val secondAd = Advertisement(
        2,
        "Мультиварка",
        "Нехорошее",
        BigDecimal(56465456),
        Category.ELECTRONICS,
        user,
        Timestamp(454564564),
        countWatch = 0,
        status = AdvertisementStatus.DECLINE_FRAUD,
        editDate = Timestamp(454564564),
        photos = arrayListOf(
            AdvertisementPhoto(
                BitmapDrawable()
            )
        )
    )

    override fun getAll(): Single<List<Advertisement>> {
        val ads = service.api.getAdvertisements()
        val list : List<AdvertisementResponse>? = ads.execute().body()
        val mapper = AdvertisementToDomainMapper()
        val categoryMapper = CategoriesToDomainMapper()
        val userMapper = UserToDomainMapper()
        return Single.just(list!!.map{
            val userResponse = service.api.getUserById(it.author).blockingGet()
            val user = userMapper.fromResponse(userResponse, service.api.getAvatarById(userResponse.avatar).blockingGet())
            val photos = service.api.getPhotoById(it.id)
            val adsPhotos = photos.map {
                AdvertisementPhoto(BitmapDrawable(null, BitmapFactory.decodeByteArray(it.image, 0, it.image.size)))
            }
            mapper.fromResponse(it, categoryMapper.fromName(it.category), user, listOf(adsPhotos.blockingGet()))
        })
    }

    override fun getAllById(userId: Long): Single<List<Advertisement>> {
        val mapper = AdvertisementToDomainMapper()
        val categoryMapper = CategoriesToDomainMapper()
        val userMapper = UserToDomainMapper()
        val userResponse = service.api.getUserById(userId).blockingGet()
        val user = userMapper.fromResponse(userResponse, service.api.getAvatarById(userResponse.avatar).blockingGet())
        val ads = service.api.getAdvertisements()
        val list : List<AdvertisementResponse>? = ads.execute().body()!!.filter { it.author == user.id }
        return Single.just(list!!.map{
            val photos = service.api.getPhotoById(it.id)
            val adsPhotos = photos.map {
                AdvertisementPhoto(BitmapDrawable(null, BitmapFactory.decodeByteArray(it.image, 0, it.image.size)))
            }
            mapper.fromResponse(it, categoryMapper.fromName(it.category), user, listOf(adsPhotos.blockingGet()))
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
        service.api.createAdvertisement(createAd)
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
        val updateAd = AdvertisementUpdate(id, header, description, price, category.name, status.name)
        service.api.updateAdvertisement(id, updateAd)
        return Completable.complete()
    }

    override fun getAllByCategory(category: Category): Single<List<Advertisement>> {
        val mapper = AdvertisementToDomainMapper()
        val categoryMapper = CategoriesToDomainMapper()
        val userMapper = UserToDomainMapper()
        val ads = service.api.getAdvertisements()
        val list : List<AdvertisementResponse>? = ads.execute().body()!!.filter { it.category == category.name }
        return Single.just(list!!.map{
            val userResponse = service.api.getUserById(it.author).blockingGet()
            val user = userMapper.fromResponse(userResponse, service.api.getAvatarById(userResponse.avatar).blockingGet())
            val photos = service.api.getPhotoById(it.id)
            val adsPhotos = photos.map {
                AdvertisementPhoto(BitmapDrawable(null, BitmapFactory.decodeByteArray(it.image, 0, it.image.size)))
            }
            mapper.fromResponse(it, categoryMapper.fromName(it.category), user, listOf(adsPhotos.blockingGet()))
        })
    }

    override fun changeAdStatus(ad: Advertisement, status: AdvertisementStatus): Completable {
        return editAd(ad.id, ad.header, ad.description, ad.price, ad.category, status)
    }

    override fun book(ad: Advertisement, userId: Long, until: Date): Completable {
        val bookingRequest = BookingRequest(ad.id, ad.price, until.toString())
        service.api.createBooking(bookingRequest)
        return Completable.complete()
    }

    override fun like(ad: Advertisement, userId: Long): Completable {
        val request = FavoriteAdvertisementsRequest(ad.id, userId)
        service.api.addToFavoriteAdvertisements(request)
        return Completable.complete()
    }

    override fun subscribe(user: User, subscriberId: Long): Completable {
        val request = FavoriteUserRequest(user.id, subscriberId)
        service.api.addToFavoriteUsers(request)
        return Completable.complete()
    }
}