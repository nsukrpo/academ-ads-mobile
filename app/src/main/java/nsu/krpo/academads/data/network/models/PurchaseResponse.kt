package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class PurchaseResponse(
        @Json(name = "id")
        val id: Long,

        @Json(name = "ads")
        val ads: Long,

        @Json(name = "seller")
        val seller: Long,

        @Json(name = "buyer")
        val buyer: Long,

        @Json(name = "price")
        val price: Double,

        @Json(name = "date")
        val date: String
)
