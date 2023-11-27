package nsu.krpo.academads.domain


data class SubscriptionUser(
        val id: Long,
        val userSubject: User,
        val userObject: User
)