package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class AdvertisementResponse(
        @Json(name = "id")
        val id: Long,

        @Json(name = "header")
        val header: String,

        @Json(name = "description")
        val description: String,

        @Json(name = "price")
        val price: Double,

        @Json(name = "category")
        val category: Int,

        @Json(name = "author")
        val author: Long,

        @Json(name = "publicationDate")
        val publicationDate: String,

        @Json(name = "countWatch")
        val countWatch: Long,

        @Json(name = "status")
        val status: String,

        @Json(name = "editDate")
        val editDate: String
)
