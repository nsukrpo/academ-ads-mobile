package nsu.krpo.academads.domain.model.ads

import java.math.BigDecimal
import java.sql.Timestamp

data class Advertisement(
    val id: Long,
    val header: String,
    val description: String,
    val price: BigDecimal,
    val category: Category,
    val author: User,
    val publicationDate: Timestamp,
    val countWatch: Int,
    val status: AdvertisementStatus,
    val editDate: Timestamp,
    val photos: ArrayList<AdvertisementPhoto>,
)
