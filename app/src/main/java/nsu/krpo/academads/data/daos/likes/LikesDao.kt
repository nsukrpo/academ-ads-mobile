package nsu.krpo.academads.data.daos.likes

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Advertisement

interface LikesDao {
    fun getById(userId: Long): Single<List<Advertisement>>
}