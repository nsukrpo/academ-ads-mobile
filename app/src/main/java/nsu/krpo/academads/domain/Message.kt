package nsu.krpo.academads.domain

import java.io.Serializable
import java.sql.Timestamp

data class Message(
        val id: Int,
        val text: String,
        val from: User,
        val to: User,
        val date: Timestamp
) : Serializable