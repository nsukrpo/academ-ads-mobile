package nsu.krpo.academads.domain.model.ads

import java.math.BigDecimal
import java.sql.Timestamp
import java.time.Instant

data class Purchase(
    val id: Long,
    val ads: Advertisement,
    val seller: User,
    val buyer: User?,
    val price: BigDecimal,
    val date: Instant
)
