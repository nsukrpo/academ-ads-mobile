package nsu.krpo.academads.data.network.models

import com.squareup.moshi.Json

//TODO: data classes for responses
data class CategoryResponse(
    @Json(name = "")
    val categoriesItems: List<Category>
)

data class Category(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String
)