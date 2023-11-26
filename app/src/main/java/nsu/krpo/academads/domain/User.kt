package nsu.krpo.academads.domain

import java.util.Date

data class User(
        val id: Long,
        val name: String,
        val avatar: Long?,
        val regDate: Date,
        val type: UserType
) : Serializable