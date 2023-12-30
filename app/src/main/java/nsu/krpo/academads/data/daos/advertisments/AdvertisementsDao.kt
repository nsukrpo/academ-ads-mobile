package nsu.krpo.academads.data.daos.advertisments

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.data.network.models.Photo
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.AdvertisementStatus
import nsu.krpo.academads.domain.model.ads.Category
import nsu.krpo.academads.domain.model.ads.User
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.Instant
import java.util.Date

interface AdvertisementsDao {

    fun getAll(): Single<List<Advertisement>>

    fun getAllById(userId: Long): Single<List<Advertisement>>

    fun getAllByCategory(category: Category): Single<List<Advertisement>>

    fun createAd(header: String, description: String, price: BigDecimal, category: Category, authorId: Long): Completable

    fun addPhotoAd(photo: ByteArray): Completable

    fun editAd(id: Long, header: String, description: String, price: BigDecimal, category: Category, status: AdvertisementStatus): Completable

    fun changeAdStatus(ad:Advertisement, status: AdvertisementStatus): Completable

    fun book(ad: Advertisement, userId: Long, until: Instant): Completable

    fun like(ad: Advertisement, userId: Long): Completable

    fun dislike(ad: Advertisement, userId: Long): Completable

    fun subscribe(user: User, subscriberId: Long): Completable



}