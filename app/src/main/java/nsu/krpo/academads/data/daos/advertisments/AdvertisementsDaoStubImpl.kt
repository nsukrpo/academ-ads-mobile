package nsu.krpo.academads.data.daos.advertisments

import com.caverock.androidsvg.SVG
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
        UsersAvatar(0, byteArrayOf(1, 123, 56, 89)),
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
                ByteArray(0)
            )
        )
    )

    val svgStr =
        "<svg width=\"60\" height=\"60\" viewBox=\"0 0 60 60\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                "<g id=\"ban-essential-web_svgrepo.com\" clip-path=\"url(#clip0_1376_917)\">\n" +
                "<g id=\"Layer 40\">\n" +
                "<path id=\"Vector\" d=\"M56.25 1.06876H3.77813C2.31284 1.06876 1.125 2.2566 1.125 3.72188V32.2688C1.125 33.734 2.31284 34.9219 3.77813 34.9219H56.25C57.7153 34.9219 58.9031 33.734 58.9031 32.2688V3.72188C58.9031 2.2566 57.7153 1.06876 56.25 1.06876Z\" stroke=\"#C80000\" stroke-opacity=\"0.7\" stroke-width=\"1.875\" stroke-linecap=\"round\" stroke-linejoin=\"round\"/>\n" +
                "<path id=\"Vector_2\" d=\"M42.6938 26.5313L34.1625 18L42.6938 9.46876L38.4657 5.24063L29.9344 13.7625L21.4032 5.24063L17.175 9.46876L25.7063 18L17.175 26.5313L21.4032 30.7594L29.9344 22.2281L38.4657 30.7594L42.6938 26.5313Z\" stroke=\"#C80000\" stroke-opacity=\"0.7\" stroke-width=\"1.875\" stroke-linecap=\"round\" stroke-linejoin=\"round\"/>\n" +
                "<path id=\"Vector_3\" d=\"M33.0094 34.9219H26.9156V58.9875H33.0094V34.9219Z\" stroke=\"#C80000\" stroke-opacity=\"0.7\" stroke-width=\"1.875\" stroke-linecap=\"round\" stroke-linejoin=\"round\"/>\n" +
                "</g>\n" +
                "</g>\n" +
                "<defs>\n" +
                "<clipPath id=\"clip0_1376_917\">\n" +
                "<rect width=\"60\" height=\"60\" fill=\"white\"/>\n" +
                "</clipPath>\n" +
                "</defs>\n" +
                "</svg>"

    val secondAd = Advertisement(
        2,
        "Мультиварка",
        "Нехорошее",
        BigDecimal(56465456),
        Category.ELECTRONICS,
        user,
        Timestamp(454564564),
        countWatch = 0,
        status = AdvertisementStatus.REJECTED,
        editDate = Timestamp(454564564),
        photos = arrayListOf(
            AdvertisementPhoto(
                ByteArray(1)
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

    override fun changeAdStatus(ad: Advertisement, status: AdvertisementStatus): Completable {
        return Completable.complete()
    }
}