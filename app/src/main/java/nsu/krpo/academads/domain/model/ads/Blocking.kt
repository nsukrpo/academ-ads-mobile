package nsu.krpo.academads.domain.model.ads

import java.time.Instant

data class Blocking(
    val id: Long,
    val blockingReason: BlockingReason,
    val time: Int,
    val blockDate: Instant
)
