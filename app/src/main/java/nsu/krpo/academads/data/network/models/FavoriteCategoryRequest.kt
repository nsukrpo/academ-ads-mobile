package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class FavoriteCategoryRequest(
        @Json(name = "category_id")
        val categoryId: Long,

        @Json(name = "user_id")
        val userId: Long
)
