package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class FavoriteAdvertisementsRequest(
        @Json(name = "ads_id")
        val adsId: Long,

        @Json(name = "user_id")
        val userId: Long
)
