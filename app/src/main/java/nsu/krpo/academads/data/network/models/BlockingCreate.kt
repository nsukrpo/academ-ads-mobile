package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class BlockingCreate(
        @Json(name = "user_id")
        val userId: Long,

        @Json(name = "reason")
        val reason: String,

        @Json(name = "otherReason")
        val otherReason: String,

        @Json(name = "time_minutes")
        val timeMinutes: Long
)
