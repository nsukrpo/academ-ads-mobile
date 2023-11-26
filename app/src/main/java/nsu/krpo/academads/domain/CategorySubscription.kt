package nsu.krpo.academads.domain

import java.io.Serializable

data class CategorySubscription(
    val id: Long,
    val user: User,
    val category: Category
) : Serializable
