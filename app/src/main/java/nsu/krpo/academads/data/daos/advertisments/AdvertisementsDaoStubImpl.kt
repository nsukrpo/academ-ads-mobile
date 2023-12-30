package nsu.krpo.academads.data.daos.advertisments


import android.graphics.drawable.BitmapDrawable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementPhoto
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.domain.model.ads.UserType
import nsu.krpo.academads.domain.model.ads.UsersAvatar
import java.math.BigDecimal
import java.time.Instant
import java.util.EnumSet
import javax.inject.Inject


class AdvertisementsDaoStubImpl @Inject constructor() : AdvertisementsDao {

    val user = User(
        1,
        "Evgeny",
        UsersAvatar(byteArrayOf(1, 123, 56, 89)),
        Instant.now(),
        EnumSet.of(UserType.USER)
    )
    val ad = Advertisement(
        1,
        "Репетитор",
        "Важный, большой",
        BigDecimal(700),
        Category.STUDY_SERVICE,
        user,
//        Timestamp.valueOf("2023-12-12 13:45:34"),
        Instant.now(),
        countWatch = 0,
        status = AdvertisementStatus.ON_ADS_BOARD,
        editDate = Instant.now(),
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
        Category.STUDY_SERVICE,
        user,
//        Timestamp.valueOf("2023-12-12 13:45:34"),
        Instant.now(),
        countWatch = 0,
        status = AdvertisementStatus.ON_ADS_BOARD,
//        editDate = Timestamp(22028900),
        editDate = Instant.now(),
        photos = arrayListOf(
            AdvertisementPhoto(
                BitmapDrawable()
            )
        )
    )
    private val adCalculator = Advertisement(
        5,
        "Программируемый калькулятор",
        "Почти новый",
        BigDecimal(1200),
        Category.EDUCATIONAL_STUFF,
        user,
//        Timestamp.valueOf("2023-02-12 13:45:34"),
        Instant.now(),
        countWatch = 0,
        status = AdvertisementStatus.ON_ADS_BOARD,
//        Timestamp.valueOf("2023-02-12 13:45:34"),
        Instant.now(),
        photos = arrayListOf(
            AdvertisementPhoto(
                BitmapDrawable()
            )
        )
    )
    val adPan = Advertisement(
        5,
        "Сковорода",
        "Антипригарное покрытик",
        BigDecimal(750),
        Category.HOUSEHOLD_APPLIANCE,
        user,
//        Timestamp.valueOf("2023-12-12 13:45:34"),
        Instant.now(),
        countWatch = 0,
        status = AdvertisementStatus.ON_ADS_BOARD,
//        Timestamp.valueOf("2023-02-12 13:45:34"),
        Instant.now(),
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
        BigDecimal(5467),
        Category.DEVICES,
        user,
//        Timestamp.valueOf("2023-12-12 13:45:34"),
        Instant.now(),
        countWatch = 0,
        status = AdvertisementStatus.DECLINE_FRAUD,
//        Timestamp.valueOf("2023-02-12 13:45:34"),
        Instant.now(),
        photos = arrayListOf(
            AdvertisementPhoto(
                BitmapDrawable()
            )
        )
    )
    override fun getAll(): Single<List<Advertisement>> = Single.just(
        listOf(
            secondAd,
            ad,
        )
    )

    override fun getAllById(userId: Long): Single<List<Advertisement>> = Single.just(
        listOf(
            secondAd,
            ad,
        )
    )


    override fun createAd(
        header: String, description: String, price: BigDecimal, category: Category, authorId: Long
    ): Completable {
        return Completable.complete()
    }

    override fun addPhotoAd(photo: ByteArray): Completable {
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
        return Completable.complete()
    }


    override fun getAllByCategory(category: Category): Single<List<Advertisement>> {
        val allItems = listOf(ad, ad3, adCalculator, secondAd, adPan)
        return when (category) {
            Category.STUDY_SERVICE -> Single.just(allItems.filter { it.category == Category.STUDY_SERVICE })


            Category.EDUCATIONAL_STUFF -> Single.just(allItems.filter { it.category == Category.EDUCATIONAL_STUFF })


            Category.HOUSEHOLD_APPLIANCE -> Single.just(allItems.filter { it.category == Category.HOUSEHOLD_APPLIANCE })


            Category.DEVICES -> Single.just(allItems.filter { it.category == Category.DEVICES })


            else -> Single.just(allItems.filter { it.category == Category.OTHER })


        }
    }

    override fun getAllByDate(date: String, category: Category): Single<List<Advertisement>> {
        return Single.just(emptyList())
    }


    override fun changeAdStatus(ad: Advertisement, status: AdvertisementStatus): Completable {
        return Completable.complete()
    }


    override fun book(ad: Advertisement, userId: Long, until: Instant): Completable {
        return Completable.complete()
    }


    override fun like(ad: Advertisement, userId: Long): Completable {
        return Completable.complete()
    }

    override fun dislike(ad: Advertisement, userId: Long): Completable {
        return Completable.complete()
    }


    override fun subscribe(user: User, subscriberId: Long): Completable {
        return Completable.complete()
    }
}



