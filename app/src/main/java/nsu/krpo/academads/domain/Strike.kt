package nsu.krpo.academads.domain

import java.io.Serializable

data class Strike(
        val id: Long,
        val user: User,
        val reason: Reason
) : Serializable
