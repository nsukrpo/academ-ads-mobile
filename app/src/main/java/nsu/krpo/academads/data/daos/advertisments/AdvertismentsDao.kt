package nsu.krpo.academads.data.daos.advertisments

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Advertisement

interface AdvertismentsDao {

    fun getAll(): Single<List<Advertisement>>
}