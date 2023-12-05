package nsu.krpo.academads.data.daos.advertisments

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Advertisement

interface AdvertisementsDao {

    fun getAll(): Single<List<Advertisement>>

    fun getAllById(userId: Long): Single<List<Advertisement>>

}