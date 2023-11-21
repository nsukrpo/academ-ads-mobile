package nsu.krpo.academads.domain

import java.io.Serializable

data class SubscriptionUser(
        val id: Long,
        val userSubject: User,
        val userObject: User
) : Serializable