package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class TokenResponse(
        @Json(name = "token")
        val token: String
)
