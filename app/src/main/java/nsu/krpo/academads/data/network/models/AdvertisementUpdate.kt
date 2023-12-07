package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class AdvertisementUpdate(
        @Json(name = "id")
        val id: Long,

        @Json(name = "header")
        val header: String,

        @Json(name = "description")
        val description: String,

        @Json(name = "price")
        val price: Double,

        @Json(name = "category")
        val category: Long,

        @Json(name = "status")
        val status: String
)
