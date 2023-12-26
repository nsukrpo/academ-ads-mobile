package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class Avatar(
        @Json(name = "image")
        val image: ByteArray
)
