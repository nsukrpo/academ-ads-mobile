package nsu.krpo.academads.domain

import java.io.Serializable

data class UsersAvatar(
        val id: Long,
        val photo: ByteArray
) : Serializable
