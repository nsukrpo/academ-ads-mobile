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
        header: String,
        description: String,
        price: BigDecimal,
        category: Category,
        authorId: Long
    ): Completable {
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
        return Single.just(
            listOf(
                ad,
                ad3,
            )
        )
    }

    override fun changeAdStatus(ad: Advertisement, status: AdvertisementStatus): Completable {
        return Completable.complete()
    }

    override fun book(ad: Advertisement, userId: Long, until: Date): Completable {
        return Completable.complete()
    }

    override fun like(ad: Advertisement, userId: Long): Completable {
        return Completable.complete()
    }

    override fun subscribe(user: User, subscriberId: Long): Completable {
        return Completable.complete()
    }
}