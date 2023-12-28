package nsu.krpo.academads.data.daos.purchases

import android.graphics.drawable.BitmapDrawable
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementPhoto
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.domain.model.ads.Purchase
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.domain.model.ads.UserType
import nsu.krpo.academads.domain.model.ads.UsersAvatar
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.Instant
import java.util.Date
import java.util.EnumSet
import javax.inject.Inject

class PurchasesDaoStubImpl @Inject constructor() : PurchasesDao {

    val user = User(
        1,
        "Evgeny",
        UsersAvatar(byteArrayOf(1, 123, 56, 89)),
        Date(18999),
        EnumSet.of(UserType.USER)
    )

    private val ad = Advertisement(
        1,
        "Репетитор",
        "Важный, большой",
        BigDecimal(700),
        Category.EDUCATIONAL_SUPPLIES,
        user,
//        Timestamp(220200),
        Instant.now(),
        countWatch = 0,
        status = AdvertisementStatus.GRANTED,
        editDate = Instant.now(),
        photos = arrayListOf(
            AdvertisementPhoto(
                BitmapDrawable()
            )
        )
    )

    private val purchase = Purchase(
        0,
        ad,
        user,
        user,
        BigDecimal(800),
        Timestamp(5545)
    )
    override fun getById(userId: Long): Single<List<Purchase>> = Single.just(
        listOf(
            purchase,
        )
    )
}