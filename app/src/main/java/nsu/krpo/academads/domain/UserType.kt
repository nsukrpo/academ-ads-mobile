package nsu.krpo.academads.domain

import java.io.Serializable

data class UserType(
        val id: Long,
        val type: String
) : Serializable
