package nsu.krpo.academads.ui.bans.bans_rv

import nsu.krpo.academads.domain.model.ads.BlockingReason
import java.time.Instant
import java.util.Date

data class BanWrapper (
    val startTime: Instant,
    val endTime: Instant,
    val reason: BlockingReason,
)