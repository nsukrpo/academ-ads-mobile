package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class UserResponse(
        @Json(name = "id")
        val id: Long,

        @Json(name = "name")
        val name: String,

        @Json(name = "avatar")
        val avatar: Long,

        @Json(name = "regDate")
        val regDate: String,

        @Json(name = "sales")
        val sales: Int,

        @Json(name = "purchases")
        val purchases: Int
)
