package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class StrikeCreate(
        @Json(name = "user")
        val user: Long,

        @Json(name = "reason")
        val reason: String
)
