package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.domain.model.ads.BlockingReason

class BlockingReasonToDomainMapper {
    fun fromString(name: String): BlockingReason {
        when(name) {
            "STRIKES_LIMIT" -> return BlockingReason.STRIKES_LIMIT
            "GROSS_VIOLATION" -> return BlockingReason.GROSS_VIOLATION
            else -> return BlockingReason.UNKNOWN
        }
    }
}