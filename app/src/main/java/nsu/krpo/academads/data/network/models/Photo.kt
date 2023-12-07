package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class Photo(
        @Json(name = "image")
        val image: ByteArray
)
