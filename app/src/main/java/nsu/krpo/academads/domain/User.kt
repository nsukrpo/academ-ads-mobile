package nsu.krpo.academads.domain

import java.util.Date
import java.util.EnumSet

data class User(
        val id: Long,
        val name: String,
        val avatar: UsersAvatar?,
        val regDate: Date,
        val type: EnumSet<UserType>
)