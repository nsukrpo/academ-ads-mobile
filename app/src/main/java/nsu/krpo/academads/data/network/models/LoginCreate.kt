package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class LoginCreate(
        @Json(name = "user_id")
        val user_id : Long,

        @Json(name = "login")
        val login: String,

        @Json(name = "password")
        val password: String
)
