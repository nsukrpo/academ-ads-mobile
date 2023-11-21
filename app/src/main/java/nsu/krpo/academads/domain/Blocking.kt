package nsu.krpo.academads.domain

import java.io.Serializable
import java.sql.Timestamp

data class Blocking(
        val id: Long,
        val user: User,
        val reason: Reason,
        val time: Int,
        val blockDate: Timestamp
) : Serializable
