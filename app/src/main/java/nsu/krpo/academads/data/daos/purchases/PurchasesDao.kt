package nsu.krpo.academads.data.daos.purchases

import io.reactivex.rxjava3.core.Single
import nsu.krpo.academads.domain.model.ads.Purchase

interface PurchasesDao {

    fun getById(userId: Long): Single<List<Purchase>>
}