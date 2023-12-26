package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class BookingResponse(
        @Json(name = "id")
        val id: Long,

        @Json(name = "ads")
        val ads: Long,

        @Json(name = "claimant")
        val claimant: Long,

        @Json(name = "dateStart")
        val dateStart: String,

        @Json(name = "dateUntil")
        val dateUntil: String
)
