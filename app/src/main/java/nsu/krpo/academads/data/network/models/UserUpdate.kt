package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class UserUpdate(
    @Json(name = "name")
    val name: String,

    @Json(name = "avatar")
    val avatar: Int
)
