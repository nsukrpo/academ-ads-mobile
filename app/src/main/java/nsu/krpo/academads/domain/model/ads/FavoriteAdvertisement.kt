package nsu.krpo.academads.domain.model.ads

import java.sql.Timestamp

data class FavoriteAdvertisement(
    val id: Long,
    val ads: Advertisement,
    val user: User,
    val dateAdd: Timestamp
)
