package nsu.krpo.academads.data.daos.bans

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Blocking
import nsu.krpo.academads.domain.model.ads.BlockingReason
import java.sql.Timestamp
import javax.inject.Inject

class BansDaoStubImpl @Inject constructor(): BansDao {


    override fun getAllByUseId(userId: Long): Single<List<Blocking>> {
        return Single.just(emptyList())
    }
}