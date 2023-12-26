package nsu.krpo.academads.domain.model.ads

import java.sql.Timestamp
import java.math.BigDecimal


data class Booking(
    val id: Long,
    val ads: Advertisement,
    val claimant: BigDecimal,
    val dateStart: Timestamp,
    val dateUntil: Timestamp
)
