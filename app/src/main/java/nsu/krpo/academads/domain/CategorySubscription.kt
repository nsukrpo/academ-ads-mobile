package nsu.krpo.academads.domain


data class CategorySubscription(
    val id: Long,
    val user: User,
    val category: Category
)
