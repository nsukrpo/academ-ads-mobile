package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class UserCreate(
    @Json(name = "name")
    val name: String
)
