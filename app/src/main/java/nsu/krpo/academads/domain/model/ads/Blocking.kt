package nsu.krpo.academads.domain.model.ads

import java.sql.Timestamp

data class Blocking(
    val id: Long,
    val blockingReason: BlockingReason,
    val time: Int,
    val blockDate: Timestamp
)
