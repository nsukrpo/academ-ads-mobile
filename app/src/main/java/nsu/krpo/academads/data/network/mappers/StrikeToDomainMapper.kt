package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.StrikeResponse
import nsu.krpo.academads.domain.model.ads.Strike

class StrikeToDomainMapper {
    fun fromResponse(response: StrikeResponse): Strike {
        val mapper = StrikeReasonToDomainMapper()
        val reason = mapper.fromString(response.reason)
        val otherReason = mapper.getOtherReason(response.reason)

        return Strike(
            id = response.id, strikeReason = reason, otherReason = otherReason
        )
    }


}