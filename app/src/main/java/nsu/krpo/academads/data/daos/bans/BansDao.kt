package nsu.krpo.academads.data.daos.bans

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Blocking

interface BansDao {

    fun getAllByUseId(userId: Long): Single<List<Blocking>>

}