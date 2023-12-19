package nsu.krpo.academads.data.daos.advertisments

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.Category
import java.math.BigDecimal

interface AdvertisementsDao {

    fun getAll(): Single<List<Advertisement>>

    fun getAllById(userId: Long): Single<List<Advertisement>>

    fun createAd(header: String, description: String, price: BigDecimal, category: Category, authorId: Long): Completable

    fun editAd(id: Long, header: String, description: String, price: BigDecimal, category: Category, status: AdvertisementStatus): Completable

    fun changeAdStatus(ad:Advertisement, status: AdvertisementStatus): Completable


}