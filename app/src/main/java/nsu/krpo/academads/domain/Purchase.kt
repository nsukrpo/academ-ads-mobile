package nsu.krpo.academads.domain

import java.io.Serializable
import java.math.BigDecimal
import java.sql.Timestamp

data class Purchase(
        val id: Long,
        val ads: Advertisement,
        val seller: User,
        val buyer: User?,
        val price: BigDecimal,
        val date: Timestamp
) : Serializable
