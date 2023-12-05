package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class BlockingUpdate(
        @Json(name = "id")
        val id: Long,

        @Json(name = "user")
        val user: Long,

        @Json(name = "reason")
        val reason: String,

        @Json(name = "time")
        val time: Long
)
