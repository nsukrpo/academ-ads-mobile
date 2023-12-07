package nsu.krpo.academads.domain.model.ads

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.sql.Timestamp

@Parcelize
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
) : Parcelable
