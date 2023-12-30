package nsu.krpo.academads.data.daos.bans

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.data.network.AcademAdsAPIService
import nsu.krpo.academads.data.network.mappers.BlockingToDomainMapper
import nsu.krpo.academads.domain.model.ads.Blocking
import javax.inject.Inject

class BansDaoWebImpl @Inject constructor(
    private val service: AcademAdsAPIService,
) : BansDao {

    override fun getAllByUseId(userId: Long): Single<List<Blocking>> = Single.just(getAllByUseIdBlocking(userId))

    fun getAllByUseIdBlocking(userId: Long): List<Blocking> {
        val res = service.getBlockingByUserId(userId).blockingGet()
        val mapper = BlockingToDomainMapper()
        return res.map {
            mapper.fromResponse(it)
        }
    }
}