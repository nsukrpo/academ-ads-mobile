package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class TokenRequest(
        @Json(name = "login")
        val login: String,

        @Json(name = "password")
        val password: String
)
