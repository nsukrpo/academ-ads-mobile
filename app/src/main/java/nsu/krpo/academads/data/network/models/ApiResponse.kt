package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

data class ApiResponse(

        @Json(name = "id")
        val id: Long?,

        @Json(name = "code")
        val code: Int?,

        @Json(name = "message")
        val message: String?,

)
