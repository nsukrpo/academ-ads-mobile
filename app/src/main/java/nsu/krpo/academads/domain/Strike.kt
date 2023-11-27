package nsu.krpo.academads.domain


data class Strike(
        val id: Long,
        val user: User,
        val reason: Reason,
        val otherReason: String?,
)
