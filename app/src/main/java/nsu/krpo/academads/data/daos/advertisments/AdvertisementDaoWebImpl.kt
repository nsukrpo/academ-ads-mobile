package nsu.krpo.academads.data.daos.advertisments

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.drawable.toBitmap
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.R
import nsu.krpo.academads.data.network.AcademAdsAPIService
import nsu.krpo.academads.data.network.mappers.AdvertisementToDomainMapper
import nsu.krpo.academads.data.network.mappers.CategoriesToDomainMapper
import nsu.krpo.academads.data.network.mappers.UserToDomainMapper
import nsu.krpo.academads.data.network.models.AdvertisementCreate
import nsu.krpo.academads.data.network.models.AdvertisementResponse
import nsu.krpo.academads.data.network.models.AdvertisementUpdate
import nsu.krpo.academads.data.network.models.Avatar
import nsu.krpo.academads.data.network.models.BookingRequest
import nsu.krpo.academads.data.network.models.FavoriteAdvertisementsRequest
import nsu.krpo.academads.data.network.models.FavoriteUserRequest
import nsu.krpo.academads.data.network.models.Photo
import nsu.krpo.academads.data.network.models.UserResponse
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementPhoto
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.domain.model.ads.number
import okhttp3.MediaType
import okhttp3.RequestBody
import java.math.BigDecimal
import java.util.Date
import javax.inject.Inject
import java.io.ByteArrayOutputStream
import java.sql.Timestamp
import java.time.Instant

class AdvertisementsDaoWebImpl @Inject constructor(
    private val context: Context,
    private val service: AcademAdsAPIService,
) : AdvertisementsDao {


    override fun getAll(): Single<List<Advertisement>> = Single.just(getAllBlocking())

    private fun getAllBlocking(): List<Advertisement> {
        val mapper = AdvertisementToDomainMapper()
        val categoryMapper = CategoriesToDomainMapper()
        val userMapper = UserToDomainMapper()
        val ads = service.getAdvertisements().execute()
        val list: List<AdvertisementResponse> =
            ads.body()!!
        return list.map {
            val userResponse = service.getUserById(it.author).execute().body()!!
            val user = userMapper.fromResponse(
                userResponse,
                getUserAvatarBlockingOrDefault(userResponse)
            )
            val photo = getAdvPhotoBlockingOrDefault(it)

            mapper.fromResponse(
                it,
                categoryMapper.fromInt(it.category),
                user,
                listOf(photo)
            )
        }
    }

    override fun getAllById(userId: Long): Single<List<Advertisement>> =
        Single.fromCallable { getAllByIdBlocking(userId) }

    private fun getAllByIdBlocking(userId: Long): List<Advertisement> {

        val list: List<Advertisement> = getAllBlocking()
        return list.filter {
            it.author.id == userId
        }
    }

    override fun createAd(
        header: String,
        description: String,
        price: BigDecimal,
        category: Category,
        authorId: Long
    ): Completable {
        val createAd = AdvertisementCreate(header, description, price, category.name, authorId)
        return try {
            val res = service.createAdvertisement(createAd).blockingGet()
            if (res.code == null) {
                Completable.complete()
            } else {
                Completable.error(Throwable(res.message))
            }
        } catch (ex: Exception) {
            Completable.error(ex)
        }
    }

    override fun addPhotoAd(photo: ByteArray): Completable {
        val mediaType = MediaType.get("application/json; charset=utf-8")
        val photo = RequestBody.create(mediaType, photo)
        return try {
            val res = service.createPhoto(photo).blockingGet()
            if (res.code == null) {
                return Completable.complete()
            } else {
                return Completable.error(Throwable(res.message))
            }
        } catch (ex: Exception) {
            return Completable.error(ex)
        }
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
            AdvertisementUpdate(id, header, description, price, category.number(), status.name)
        return try {
            service.updateAdvertisement(id, updateAd).blockingGet()
            service.updateAdvertisement(id, updateAd).blockingGet()
            Completable.complete()
        } catch (ex: Exception) {
            Completable.error(ex)
        }
    }

    override fun getAllByCategory(category: Category): Single<List<Advertisement>> =
        Single.fromCallable { getAllByCategoryBlocking(category) }

    private fun getAllByCategoryBlocking(category: Category): List<Advertisement> {

        val list: List<Advertisement> =
            getAllBlocking()
        return list.filter {
            ((it.category == category) && (it.status == AdvertisementStatus.ON_ADS_BOARD))
        }
    }

    private fun getUserAvatarBlockingOrDefault(userResponse: UserResponse): Avatar {
        if (userResponse.avatar == null) {
            val drawable = context.resources.getDrawable(R.drawable.seller)
            val st = ByteArrayOutputStream()
            drawable.toBitmap(drawable.intrinsicHeight, drawable.intrinsicWidth)
                .compress(Bitmap.CompressFormat.PNG, 100, st)
            return Avatar(st.toByteArray())
        }

        //TODO add try/catch if Avatar by ID not found
        return service.getAvatarById(userResponse.avatar).blockingGet()
    }

    private fun getAdvPhotoBlockingOrDefault(advResponse: AdvertisementResponse): AdvertisementPhoto {
        if (advResponse.id == null) {
            val drawable = context.resources.getDrawable(R.drawable.camera)
            return AdvertisementPhoto(drawable)
        }


        try {
            val photoStr = service.getPhotoById(advResponse.id).blockingGet()
            val photo = Photo(photoStr.bytes())
            return AdvertisementPhoto(
                BitmapDrawable(
                    null,
                    BitmapFactory.decodeByteArray(photo.image, 0, photo.image.size)
                )
            )
        } catch (e: Exception) {
            val drawable = context.resources.getDrawable(R.drawable.camera)
            return AdvertisementPhoto(drawable)
        }
    }

    override fun changeAdStatus(ad: Advertisement, status: AdvertisementStatus): Completable {
        return editAd(ad.id, ad.header, ad.description, ad.price, ad.category, status)
    }

    override fun book(ad: Advertisement, userId: Long, until: Instant): Completable {
        val bookingRequest = BookingRequest(ad.id, userId, until.toString())
        return try {
            service.createBooking(bookingRequest).blockingGet()
            Completable.complete()
        }catch (ex: Exception) {
            Completable.error(ex)
        }
    }

    override fun like(ad: Advertisement, userId: Long): Completable {
        val request = FavoriteAdvertisementsRequest(ad.id, userId)
        try {
            val result = service.addToFavoriteAdvertisements(request).blockingGet()
            if (result.id != null) {
                return Completable.complete()
            }
            return Completable.error(Throwable(result.message))

        } catch (e: Exception) {
            return Completable.error(Throwable(e.message))
        }

    }

    override fun dislike(ad: Advertisement, userId: Long): Completable {
        return try {
            val result = service.removeFromFavorites(userId, ad.id).blockingGet()
            if (result.code != 200) {
                Completable.error(Throwable(result.message))
            } else {
                Completable.complete()
            }
        } catch (ex: Exception) {
            Completable.error(Throwable(ex.message))
        }

    }

    override fun subscribe(user: User, subscriberId: Long): Completable {
        val request = FavoriteUserRequest(user.id, subscriberId)
        service.addToFavoriteUsers(request)
        return Completable.complete()
    }
}