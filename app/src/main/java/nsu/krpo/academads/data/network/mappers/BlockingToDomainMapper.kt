package nsu.krpo.academads.data.network.mappers

import nsu.krpo.academads.data.network.models.*
import nsu.krpo.academads.domain.model.ads.*

class BlockingToDomainMapper {
    fun fromResponse(response: BlockingResponse): Blocking {
        val user = User(response.user)
        val reason = Reason(response.reason)

        return Blocking(
                id = response.id,
                user = user,
                reason = response.reason,
                otherReason = response.otherReason,
                time = response.time.toInt(),
                blockDate = Timestamp.valueOf(response.blockDate)
        )
    }

    fun fromUpdate(update: BlockingUpdate): Blocking {
        val user = User(update.user)
        val reason = Reason(response.reason)

        return Blocking(
                id = update.id,
                user = user,
                reason = reason,
                otherReason = update.otherReason,
                time = update.time.toInt(),
                blockDate = /* достать из бд */
        )
    }
    fun fromCreate(create: BlockingUpdate): Blocking {
        val user = User(create.userId)
        val reason = Reason(create.reason)

        return Blocking(
                id = /* сгенерировать */,
                user = user,
                reason = reason,
                otherReason = create.otherReason,
                time = create.timeMinutes.toInt(),
                blockDate = Timestamp(System.currentTimeMillis())
        )
    }

}