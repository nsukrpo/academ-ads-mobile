class MessageToDomainMapper {
    fun fromCreate(create: MessageCreate): Message {
        val fromUser = User(create.from)
        val toUser = User(create.to)
        val messageId = /* сгенерировать */
        val currentDate = Timestamp(System.currentTimeMillis())

        return Message(
                id = messageId,
                text = create.text,
                from = fromUser,
                to = toUser,
                date = currentDate
        )
    }

    fun fromResponse(response: MessageResponse): Message {
        val fromUser = User(response.from)
        val toUser = User(response.to)
        val timestamp = Timestamp.valueOf(response.date)

        return Message(
                id = response.id,
                text = response.text,
                from = fromUser,
                to = toUser,
                date = timestamp
        )
    }
}