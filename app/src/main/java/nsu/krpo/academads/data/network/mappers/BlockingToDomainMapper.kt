package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.BlockingResponse
import nsu.krpo.academads.domain.model.ads.Blocking
import java.sql.Timestamp

class BlockingToDomainMapper {

    fun fromResponse(response: BlockingResponse): Blocking {
        val reason = BlockingReasonToDomainMapper().fromString(response.reason)

        return Blocking(
            id = response.id,
            blockingReason = reason,
            time = response.time.toInt(),
            blockDate = Timestamp.valueOf(response.blockDate)
        )
    }

}