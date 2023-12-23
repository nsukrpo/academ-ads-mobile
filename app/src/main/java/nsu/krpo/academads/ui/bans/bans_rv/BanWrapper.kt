package nsu.krpo.academads.ui.bans.bans_rv

import nsu.krpo.academads.domain.model.ads.BlockingReason
import java.util.Date

data class BanWrapper (
    val startTime: Date,
    val endTime: Date,
    val reason: BlockingReason,
)