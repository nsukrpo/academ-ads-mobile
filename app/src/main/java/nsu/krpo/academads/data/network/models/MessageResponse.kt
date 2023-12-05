package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class MessageResponse(
        @Json(name = "id")
        val id: Long,

        @Json(name = "text")
        val text: String,

        @Json(name = "from")
        val from: Long,

        @Json(name = "to")
        val to: Long,

        @Json(name = "date")
        val date: String
)
