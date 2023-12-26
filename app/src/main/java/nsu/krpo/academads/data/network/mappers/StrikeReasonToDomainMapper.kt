package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.domain.model.ads.StrikeReason

class StrikeReasonToDomainMapper {
    fun fromString(name: String): StrikeReason {
        return when(name) {
            "RUDE_WORDS" -> StrikeReason.OBSCENE_LANG
            "NUDITY" -> StrikeReason.NUDITY
            "VIOLENCE" -> StrikeReason.VIOLENCE
            "FRAUD" -> StrikeReason.SPAM
            else -> StrikeReason.OTHER
        }
    }

    fun getOtherReason(name: String): String {
        return when(name) {
            "RUDE_WORDS" -> ""
            "NUDITY" -> ""
            "VIOLENCE" -> ""
            "FRAUD" -> ""
            else -> name
        }
    }
}