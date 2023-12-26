package nsu.krpo.academads.domain.model.ads


data class Strike(
    val id: Long,
    val strikeReason: StrikeReason,
    val otherReason: String?,
)
