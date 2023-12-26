package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class FavoriteUserRequest(
        @Json(name = "subject_id")
        val categoryId: Long,

        @Json(name = "object_id")
        val userId: Long
)
