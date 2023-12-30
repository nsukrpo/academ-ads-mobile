package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class IdResponse(
    @Json(name = "id")
    val id: Long,
)
