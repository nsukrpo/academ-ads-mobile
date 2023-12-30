package nsu.krpo.academads.data.network.mappers
import nsu.krpo.academads.data.network.models.PurchaseResponse
import nsu.krpo.academads.domain.model.ads.Advertisement
import nsu.krpo.academads.domain.model.ads.Purchase
import nsu.krpo.academads.domain.model.ads.User
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.ZonedDateTime

class PurchaseToDomainMapper {
    fun fromResponse(
        response: PurchaseResponse,
        ad: Advertisement,
        seller: User,
        buyer: User,
        ): Purchase {

        val timestamp = ZonedDateTime.parse(response.date).toInstant()


        return Purchase(
            id = response.id,
            ads = ad,
            seller = seller,
            buyer = buyer,
            price = BigDecimal.valueOf(response.price),
            date = timestamp
        )
    }
}