package nsu.krpo.academads.domain

import java.io.Serializable
import java.sql.Timestamp

data class Booking(
        val id: Long,
        val ads: Advertisement,
        val claimant: BigDecimal,
        val dateStart: Timestamp,
        val dateUntil: Timestamp
) : Serializable
