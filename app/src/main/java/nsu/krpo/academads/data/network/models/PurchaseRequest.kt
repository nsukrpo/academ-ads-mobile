package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class PurchaseRequest(
        @Json(name = "ads_id")
        val adsId: Long,

        @Json(name = "seller_id")
        val sellerId: Long,

        @Json(name = "buyer_id")
        val buyerId: Long,

        @Json(name = "price")
        val price: Double
)
