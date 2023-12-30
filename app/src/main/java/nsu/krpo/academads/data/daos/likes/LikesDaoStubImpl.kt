package nsu.krpo.academads.data.daos.likes

import android.graphics.drawable.BitmapDrawable
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

class LikesDaoStubImpl @Inject constructor() : LikesDao {

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
        Category.EDUCATIONAL_STUFF,
        user,
//        Timestamp(220200),
        Instant.now(),
        countWatch = 0,
        status = AdvertisementStatus.ON_ADS_BOARD,
//        editDate = Timestamp(5656),
        editDate = Instant.now(),
        photos = arrayListOf(
            AdvertisementPhoto(
                BitmapDrawable()
            )
        )
    )

    override fun getById(userId: Long): Single<List<Advertisement>> = Single.just(
        listOf(
            ad
        )
    )
}