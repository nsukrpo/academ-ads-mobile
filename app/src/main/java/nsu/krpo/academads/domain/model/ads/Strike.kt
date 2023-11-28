package nsu.krpo.academads.domain.model.ads


data class Strike(
    val id: Long,
    val user: User,
    val reason: Reason,
    val otherReason: String?,
)
