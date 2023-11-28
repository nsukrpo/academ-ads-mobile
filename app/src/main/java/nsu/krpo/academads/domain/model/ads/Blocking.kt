package nsu.krpo.academads.domain.model.ads

import java.sql.Timestamp

data class Blocking(
    val id: Long,
    val user: User,
    val reason: Reason,
    val otherReason: String?,
    val time: Int,
    val blockDate: Timestamp
)
