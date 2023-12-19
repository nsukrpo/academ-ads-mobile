package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.*
import nsu.krpo.academads.domain.model.ads.*

class PurchaseToDomainMapper {
    fun fromResponse(response: PurchaseResponse): Purchase {
        val advertisement = Advertisement(id = response.ads)
        val seller = User(id = response.seller)
        val buyer = if (response.buyer != null) User(id = response.buyer) else null

        val timestamp = Timestamp.valueOf(response.date)

        return Purchase(
                id = response.id,
                ads = advertisement,
                seller = seller,
                buyer = buyer,
                price = BigDecimal.valueOf(response.price),
                date = timestamp
        )
    }
}