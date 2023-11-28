package nsu.krpo.academads.domain.model.ads


data class CategorySubscription(
    val id: Long,
    val user: User,
    val category: Category
)
