package nsu.krpo.academads.data.daos.advertisments

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.domain.model.ads.User
import nsu.krpo.academads.domain.model.ads.UserType
import java.math.BigDecimal
import java.sql.Timestamp
import java.util.Date
import java.util.EnumSet
import javax.inject.Inject

class AdvertismentsDaoImpl @Inject constructor(): AdvertismentsDao {

    override fun getAll(): Single<List<Advertisement>> = Single.just(
        listOf(
            Advertisement(
                1,
                "Репетитор",
                "Важный, большой",
                BigDecimal(700),
                Category.EDUCATIONAL_SUPPLIES,
                User(
                    1,
                    "Evgeny",
                    null,
                    Date(18999),
                    EnumSet.of(UserType.USER)
                ),
                Timestamp(220200),
                countWatch = 0,
                status = AdvertisementStatus.GRANTED,
                editDate = Timestamp(5656),
                photos = ArrayList()
            )
        )
    )
}