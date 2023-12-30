package nsu.krpo.academads.data.daos.recommendations

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


class RecommendationsDaoStubImpl @Inject constructor(): RecommendationsDao {

    private val user = User(
        0, "Roman", UsersAvatar(ByteArray(5)), Instant.now(), EnumSet.of(UserType.USER))

    private val adCow = Advertisement (
        0,
        "Корова",
        "Такая корова, что самому нужна",
        BigDecimal(1200),
        Category.OTHER,
        user,
        Instant.parse("2023-12-22T00:00:00Z"),
        0,
        AdvertisementStatus.ON_ADS_BOARD,
        Instant.now().minusSeconds(50000),
        arrayListOf(AdvertisementPhoto(BitmapDrawable()))
    )
    override fun getAllByUserId(userId: Long): Single<List<Advertisement>> {
        return Single.just(
            listOf(adCow)
        )
    }
}