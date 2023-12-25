package nsu.krpo.academads.data.daos.recommendations

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Advertisement

interface RecommendationsDao {
    fun getAllByUserId(userId: Long): Single<List<Advertisement>>
}