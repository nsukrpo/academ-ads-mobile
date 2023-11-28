package nsu.krpo.academads.domain.model.ads


data class SubscriptionUser(
    val id: Long,
    val userSubject: User,
    val userObject: User
)