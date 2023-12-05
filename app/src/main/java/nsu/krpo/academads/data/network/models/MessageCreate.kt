package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class MessageCreate(
        @Json(name = "text")
        val text: String,

        @Json(name = "from")
        val from: Long,

        @Json(name = "to")
        val to: Long,

)
