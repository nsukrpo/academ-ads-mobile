package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json
import java.math.BigDecimal

data class BookingRequest       (
        @Json(name = "ads_id")
        val adsId: Long,

        @Json(name = "claimant")
        val claimant: Long,

        @Json(name = "dateUntil")
        val dateUntil: String
)
