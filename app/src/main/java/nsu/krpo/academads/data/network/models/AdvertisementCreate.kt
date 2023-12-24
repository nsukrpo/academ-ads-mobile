package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json
import java.math.BigDecimal

data class AdvertisementCreate(
        @Json(name = "header")
        val header: String,

        @Json(name = "description")
        val description: String,

        @Json(name = "price")
        val price: BigDecimal,

        @Json(name = "category")
        val category: String,

        @Json(name = "author")
        val author: Long
)
