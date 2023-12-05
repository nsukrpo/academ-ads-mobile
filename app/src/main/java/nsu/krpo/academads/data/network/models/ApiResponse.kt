package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class ApiResponse(
        @Json(name = "code")
        val code: String,

        @Json(name = "message")
        val message: String?,

)
