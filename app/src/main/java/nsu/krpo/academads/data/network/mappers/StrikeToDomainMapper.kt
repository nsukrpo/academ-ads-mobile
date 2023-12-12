class StrikeToDomainMapper {
    fun fromResponse(response: StrikeResponse): Strike {
        val user = User(id = response.user)
        val reason = Reason(response.reason)

        return Strike(
                id = response.id,
                user = user,
                reason = reason,
                otherReason = response.otherReason
        )
    }

    fun fromCreate(create: StrikeCreate): Strike {
        val user = User(id = create.user)
        val reason = Reason(create.reason)

        return Strike(
                id = /* сгенерировать */,
                user = user,
                reason = reason,
                otherReason = create.otherReason
        )
    }

    fun fromUpdate(update: StrikeUpdate): Strike {
        val user = User(id = update.user)
        val reason = Reason(update.reason)

        return Strike(
                id = update.id,
                user = user,
                reason = reason,
                otherReason = update.otherReason
        )
    }
}